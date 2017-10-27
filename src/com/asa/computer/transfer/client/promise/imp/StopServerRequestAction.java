package com.asa.computer.transfer.client.promise.imp;

import com.asa.base.io.IOUtils;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by andrew_asa on 2017/8/17.
 * 停止服务器请求
 */
public class StopServerRequestAction extends AbstractRequestAction {

    @Override
    public short getCmd() {

        return RequestConstant.CMD_STOPSERVER;
    }

    @Override
    public Request getRequest(Object... args) {

        Request request = new Request();
        RequestHeader header = request.getHeader();
        header.setCmd(RequestConstant.CMD_STOPSERVER);
        header.setBodyLen(0);
        return request;
    }

    @Override
    public RequestActionResult actionRequest(short cmd, Request request, String ip, int port) {

        RequestActionResult ret = new RequestActionResult(RequestConstant.ACTION_RESULT_FAIL);
        if (request != null) {
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            try {
                socket = new Socket(ip, port);
                out = socket.getOutputStream();
                out.write(request.toBytes());
            } catch (UnknownHostException e) {
                ret.setMessage("UnknownHost");
            } catch (IOException e) {
                ret.setMessage("error in send command");
            } finally {
                IOUtils.closeQuietly(in, out, socket);
            }
        } else {
            // 构建请求的时候就出错了
            ret.setMessage("error in build request");
        }
        return ret;
    }
}
