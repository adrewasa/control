package com.asa.computer.transfer.client.promise.imp;

import com.asa.base.log.LoggerUtils;
import com.asa.base.utils.data.GeneralUtils;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestBody;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

/**
 * @author andrew.asa
 * @create 2017-11-17
 **/
public abstract class BaseGetFileAction extends AbstractRequestAction {

    Logger LOGGER = LoggerUtils.getLogger(this.getClass());

    @Override
    public Request getRequest(Object... args) throws Exception {

        // 应该给文件或文件夹名字
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
            try {
                socket = new Socket(ip, port);
                out = socket.getOutputStream();
                // 要求获取的资源
                out.write(request.toBytes());
                in = socket.getInputStream();
                receiveFromServer(request, in, out, ret);
            } catch (UnknownHostException e) {
                ret.setMessage("UnknownHost");
            } catch (IOException e) {
                ret.setMessage("unreachable ip:" + ip + " port:" + port);
            } catch (Exception e) {

            } finally {
                // 关闭相关资源
                IOUtils.closeQuietly(in, out, socket);
            }
        } else {
            ret.setMessage("error params");
        }
        return ret;
    }


    abstract void receiveFromServer(Request request, InputStream in, OutputStream out, RequestActionResult ret) throws Exception;

    /**
     * 获取随机名字
     *
     * @return
     */
    protected String getRandomName() {

        Date date = new Date();
        StringBuffer sb = new StringBuffer();
        sb.append(date.getTime());
        return sb.toString();
    }

    protected String getFileOrDirName(Request request) throws Exception {

        return new String(request.getBody().toBytes(), "utf-8");
    }

    /**
     * 接收一个文件
     *
     * @param to
     * @param from
     */
    protected void receiveFile(File to, InputStream from) {

    }
}
