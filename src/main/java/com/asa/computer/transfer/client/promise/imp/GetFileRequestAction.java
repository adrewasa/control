package com.asa.computer.transfer.client.promise.imp;

import com.asa.base.log.LoggerUtils;
import com.asa.base.utils.transport.TransportUtils;
import com.asa.computer.transfer.Constant;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.server.promise.imp.data.GetFileResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

/**
 * Created by andrew_asa on 2017/8/16.
 * 获取文件请求
 */
public class GetFileRequestAction extends BaseGetFileAction {

    Logger LOGGER = LoggerUtils.getLogger(this.getClass());


    @Override
    public short getCmd() {

        return RequestConstant.CMD_GET_FILE;
    }

    void receiveFromServer(Request request, InputStream in, OutputStream out, RequestActionResult ret) throws Exception {

        File file = null;
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
            long step = revFileLen / 100;
            long sr = step;
            for (count = 0L; -1 != (n = in.read(buffer)); count += (long) n) {
                output.write(buffer, 0, n);
                if (count > sr) {
                    System.out.println(TransportUtils.revPercent(count, revFileLen) + "% have receive");
                    sr += step;
                }
            }
            output.flush();
            IOUtils.closeQuietly(output);
            long fsize = FileUtils.sizeOf(file);
            if (fsize == revFileLen) {
                // 传输的文件大小不对，说明传输过程出错了
                String rname = getFileOrDirName(request);
                rname = FilenameUtils.getName(rname);
                File r = new File(FilenameUtils.concat(Constant.TRANSPORTREVEIVEPATH, rname));
                if (r.exists()) {
                    r = new File(FilenameUtils.concat(Constant.TRANSPORTREVEIVEPATH, getRandomName() + rname));
                }
                file.renameTo(r);
                long endTime = System.currentTimeMillis();
                ret.setStatus(RequestConstant.ACTION_RESULT_SUCCESS);
                LOGGER.info("receive {} kb cost {} second  rate {} mb/s  ", com.asa.base.utils.transport.TransportUtils.fileSizeKb(fsize), com.asa.base.utils.transport.TransportUtils.costSecond(startTime, endTime), com.asa.base.utils.transport.TransportUtils.transportRateMb(startTime, endTime, fsize));
            } else {
                ret.setMessage("ile not same promise size");
                LOGGER.info("file not same size,promise size is {} byte，but really get {} byte", revFileLen, fsize);
            }
        } else {
            ret.setMessage(response.getDescription());
        }
    }
}
