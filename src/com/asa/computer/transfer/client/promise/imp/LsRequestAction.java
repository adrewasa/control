package com.asa.computer.transfer.client.promise.imp;

import com.asa.computer.transfer.Constant;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestBody;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;
import com.asa.utils.applet.ls.LsNode;
import com.asa.utils.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by andrew_asa on 2017/8/16.
 * ls 服务器目录
 */
public class LsRequestAction extends AbstractRequestAction {

    @Override
    public short getCmd() {

        return RequestConstant.CMD_LS;
    }

    @Override
    public Request getRequest(Object... args) {

        Request ret = new Request();
        RequestHeader header = ret.getHeader();
        RequestBody body = ret.getBody();
        header.setCmd(getCmd());
        // 如果是获取目录,第一个参数为目录的名字,默认为"./"
        if (args.length > 0) {
            // 如果有参数则第一个参数就是的路径
            String dir = (String) args[0];
            body.append(dir);
            header.setBodyLen(body.length());
        } else {
            header.setBodyLen(0);
        }
        return ret;
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
                in = socket.getInputStream();
                byte[] rev = new byte[Constant.DATABUFLEN];
                int revLen = in.read(rev);
                LsNode lsNode = new LsNode();
                int r = lsNode.parse(rev, 0, revLen);
                if (r == 0) {
                    ret.setResponse(lsNode);
                    ret.setStatus(RequestConstant.ACTION_RESULT_SUCCESS);
                }
            } catch (IOException e) {
                ret.setMessage("error in get lsNode");
                e.printStackTrace();
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
