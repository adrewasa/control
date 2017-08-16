package com.asa.computer.transfer.server;

import com.asa.computer.transfer.Constant;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestBody;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;
import com.asa.utils.applet.ls.Ls;
import com.asa.utils.applet.ls.LsNode;

import java.io.File;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by andrew_asa on 2017/7/26.
 */
public class RequestActionFactory {

    public static ResponseActionResult actionRequest(Request request, Socket s) {

        ResponseActionResult result = new ResponseActionResult(ResponseConstant.ACTION_RESULT_SUCCESS);
        try {
            RequestHeader header = request.getHeader();
            RequestBody body = request.getBody();
            byte[] bodyData = body.toBytes();
            short requestType = header.getCmd();
            OutputStream out = s.getOutputStream();
            // 列出文件列表
            if (requestType == RequestConstant.CMD_LS) {
                String path = Constant.TRANSPORTBASEPATH;
                if (header.getBodyLen() > 0) {
                    String t = new String(bodyData, 0, header.getBodyLen());
                    if (basePathCheck(t)) {
                        path = t;
                    }
                }
                Ls ls = new Ls(path);
                LsNode lsNode = ls.getSimpleLsNode();
                byte[] outBytes = lsNode.toBytes();
                out.write(outBytes);
            } else if (requestType == RequestConstant.CMD_GET_FILE) {
                // 获取文件路径
                if (header.getBodyLen() > 0) {
                    String filePath = new String(bodyData, 0, header.getBodyLen());
                    if (basePathCheck(filePath)) {
                        File f = new File(filePath);
                        if (f.exists()) {
                            if (f.isFile()) {

                            } else if(f.isDirectory()){
                                // 压缩文件进行传输
                            }
                        } else {
                            // 文件一般都是存在的。。
                        }
                    }
                }

            } else if (requestType == RequestConstant.CMD_STOPSERVER) {
                // 停止服务器
                result.setStatus(ResponseConstant.ACTION_RESULT_STOP_SERVER);
            } else if (requestType == RequestConstant.CMD_UPDATA_SERVER) {
                // 更新服务器
                // TODO 这个命令的安全隐患太大了,需要更了解密码学的时候再进行编写
            }
        } catch (Exception e) {

        }
        return result;
    }

    private static boolean basePathCheck(String p) {

        return p != null && p.startsWith(Constant.TRANSPORTBASEPATH) && !p.contains("..");
    }
}
