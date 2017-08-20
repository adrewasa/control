package com.asa.computer.transfer.client.promise.imp;

import com.asa.computer.transfer.Constant;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestBody;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;
import com.asa.computer.transfer.server.promise.imp.data.GetFileResponse;
import com.asa.utils.data.GeneralUtils;
import com.asa.utils.io.IOUtils;
import com.asa.utils.log.LoggerUtils;
import com.asa.utils.transport.TransportUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by andrew_asa on 2017/8/16.
 * 获取文件请求
 */
public class GetFileRequestAction extends AbstractRequestAction {

    Logger LOGGER = LoggerUtils.getLogger(this.getClass());


    @Override
    public short getCmd() {

        return RequestConstant.CMD_GET_FILE;
    }

    @Override
    public Request getRequest(Object... args) throws UnsupportedEncodingException {

        // 应该给文件路径
        if (args.length < 1) {
            return null;
        }
        Request ret = new Request();
        RequestHeader header = ret.getHeader();
        RequestBody body = ret.getBody();
        header.setCmd(getCmd());
        // 如果有参数则第一个参数就是的路径
        String dir = (String) args[0];
        body.append(dir.getBytes("utf-8"));
        header.setBodyLen(body.length());
        return ret;
    }

    @Override
    public RequestActionResult actionRequest(short cmd, Request request, String ip, int port) {

        RequestActionResult ret = new RequestActionResult(RequestConstant.ACTION_RESULT_FAIL);
        if (GeneralUtils.allNotNull(request, ip, port)) {
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            File file = null;
            try {
                socket = new Socket(ip, port);
                out = socket.getOutputStream();
                out.write(request.toBytes());
                in = socket.getInputStream();
                //
                byte[] rev = new byte[1024];
                int revLen = in.read(rev);
                GetFileResponse response = GetFileResponse.getGetFileResponse(GetFileResponse.RESPONSE_TYPE_START_TRANSPORT);
                response.parse(rev, 0, revLen);
                if (response.canGetFile()) {
                    // 开始接收回复
                    GetFileResponse receive = GetFileResponse.getGetFileResponse(GetFileResponse.RESPONSE_TYPE_START_RECEIVE);
                    out.write(receive.toBytes());
                    out.flush();
                    // 写进行写到临时文件，然后再进行更换名字
                    String c = FilenameUtils.concat(Constant.getTransportReveivePath(), getRandomName());
                    FileUtils.forceMkdirParent(new File(c));
                    GetFileResponse.StartTransportBody responseBody = (GetFileResponse.StartTransportBody) response.getBody();
                    // 接收的文件大小
                    long revFileLen = responseBody.getFileLen();
                    LOGGER.info("start rev file from server, size {} kb", TransportUtils.fileSizeKb(revFileLen));
                    file = new File(c);
                    file.createNewFile();
                    long startTime = System.currentTimeMillis();
                    long count;
                    int n;
                    FileOutputStream output = FileUtils.openOutputStream(file);
                    byte[] buffer = new byte[4096];
                    long step = revFileLen/100;
                    long sr = step;
                    for (count = 0L; -1 != (n = in.read(buffer)); count += (long) n) {
                        output.write(buffer, 0, n);
                        if (count > sr) {
                            System.out.println(TransportUtils.revPercent(count,revFileLen)+"% have receive");
                            sr+=step;
                        }
                    }
                    IOUtils.closeQuietly(out);
                    //FileUtils.copyInputStreamToFile(in, file);
                    long fsize = FileUtils.sizeOf(file);
                    if (fsize == revFileLen) {
                        // 传输的文件大小不对，说明传输过程出错了
                        String rname = new String(request.getBody().toBytes(), "utf-8");
                        rname = FilenameUtils.getName(rname);
                        File r = new File(FilenameUtils.concat(Constant.TRANSPORTREVEIVEPATH, rname));
                        if (r.exists()) {
                            r = new File(FilenameUtils.concat(Constant.TRANSPORTREVEIVEPATH, getRandomName() + rname));
                        }
                        file.renameTo(r);
                        long endTime = System.currentTimeMillis();
                        ret.setStatus(RequestConstant.ACTION_RESULT_SUCCESS);
                        LOGGER.info("receive {} kb cost {} second  rate {} mb/s  ", TransportUtils.fileSizeKb(fsize), TransportUtils.costSecond(startTime, endTime), TransportUtils.transportRateMb(startTime, endTime, fsize));
                    } else {
                        ret.setMessage("ile not same promise size");
                        LOGGER.info("file not same size,promise size is {} byte，but really get {} byte", revFileLen, fsize);
                    }
                } else {
                    ret.setMessage(response.getDescription());
                }
            } catch (UnknownHostException e) {
                ret.setMessage("UnknownHost");
            } catch (IOException e) {
                ret.setMessage("unreachable ip:" + ip + " port:" + port);
            } finally {
                // 关闭相关资源
                IOUtils.closeQuietly(in, out, socket);
                //把临时文件删除了
                if (file != null) {
                    try {
                        file.delete();
                    } catch (Exception e) {

                    }
                }
            }
        } else {
            ret.setMessage("error params");
        }
        return ret;
    }

    /**
     * 获取随机名字
     *
     * @return
     */
    private String getRandomName() {

        Date date = new Date();
        StringBuffer sb = new StringBuffer();
        sb.append(date.getTime());
        return sb.toString();
    }
}
