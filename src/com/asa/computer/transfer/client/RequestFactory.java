package com.asa.computer.transfer.client;

import com.asa.computer.transfer.Constant;
import com.asa.utils.applet.ls.Ls;
import com.asa.utils.applet.ls.LsNode;
import com.asa.utils.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by andrew_asa on 2017/7/26.
 */
public class RequestFactory {


    /**
     * 构造一个请求的命令
     *
     * @param cmd  命令类型
     * @param args 参数
     * @return
     */
    public static Request getRequst(short cmd, Object... args) {

        Request ret = new Request();
        RequestHeader header = ret.getHeader();
        RequestBody body = ret.getBody();
        header.setCmd(cmd);

        if (cmd == RequestConstant.CMD_LS) {
            // 如果是获取目录,第一个参数为目录的名字,默认为"./"
            if (args.length > 0) {
                // 如果有参数则第一个参数就是的路径
                String dir = (String) args[0];
                body.append(dir);
                //
                header.setBodyLen(body.length());
            }
        } else if (cmd == RequestConstant.CMD_GET_FILE) {

        } else if (cmd == RequestConstant.CMD_STOPSERVER) {

        }
        return ret;
    }

    /**
     * 根据返回的byte数据解析成为一个request
     *
     * @param bytes
     * @param start
     * @param len
     * @return
     */
    public static Request getRequest(byte[] bytes, int start, int len) {

        int offset = start;
        Request ret = new Request();
        RequestHeader header = ret.getHeader();
        RequestBody body = ret.getBody();
        int h = header.parse(bytes, start, len);
        if (h != 0) {
            ret.setBroken(offset);
            return ret;
        }
        offset += header.size();
        int b = body.parse(bytes, offset, len);
        if (b != 0) {
            ret.setBroken(offset);
            return ret;
        }
        return ret;
    }

    public static RequestActionResult actionRequest(Request request) {

        RequestActionResult ret = new RequestActionResult(RequestConstant.ACTION_RESULT_SUCCESS);
        if (request != null) {
            short cmd = request.getHeader().getCmd();
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            try {
                socket = new Socket(Constant.LOCALHOSTADDR, Constant.SERVERPORT);
                out = socket.getOutputStream();
                out.write(request.toBytes());
                if (cmd == RequestConstant.CMD_LS) {
                    in = socket.getInputStream();
                    byte[] rev = new byte[Constant.DATABUFLEN];
                    int revLen = in.read(rev);
                    LsNode lsNode = new LsNode();
                    int r = lsNode.parse(rev, 0, revLen);
                    if (r == 0) {
                        Ls.printfLsNode(lsNode);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(in, out, socket);
            }
        }
        return ret;
    }
}
