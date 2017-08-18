package com.asa.computer.transfer.client.promise.imp;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestBody;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;
import com.asa.computer.transfer.server.promise.imp.data.GetFileResponse;
import com.asa.utils.data.GeneralUtils;
import com.asa.utils.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by andrew_asa on 2017/8/16.
 * 获取文件请求
 */
public class GetFileRequestAction extends AbstractRequestAction {

    @Override
    public short getCmd() {

        return RequestConstant.CMD_GET_FILE;
    }

    @Override
    public Request getRequest(Object... args) {

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
        body.append(dir);
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
                out.write(request.toBytes());
                in = socket.getInputStream();
                //
                byte[] rev = new byte[2];
                int revLen = in.read(rev);
                GetFileResponse response = GetFileResponse.getGetFileResponse(GetFileResponse.RESPONSE_TYPE_NULL);
                response.parse(rev, 0, revLen);
                if (response.canGetFile()) {
                    // 开始接收回复
                    GetFileResponse receive = GetFileResponse.getGetFileResponse(GetFileResponse.RESPONSE_TYPE_START_RECEIVE);
                    out.write(receive.toBytes());
                    out.flush();
                    //
                } else {
                    ret.setMessage(response.getDescription());
                }
            } catch (UnknownHostException e) {
                ret.setMessage("UnknownHost");
            } catch (IOException e) {
                ret.setMessage("unreachable ip:" + ip + " port:" + port);
            } finally {
                IOUtils.closeQuietly(in, out, socket);
            }
        } else {
            ret.setMessage("error params");
        }
        return ret;
    }


}
