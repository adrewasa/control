package com.asa.computer.transfer.server;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;
import com.asa.utils.applet.ls.Ls;
import com.asa.utils.applet.ls.LsNode;

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
            short requestType = header.getCmd();
            // 列出文件列表
            if (requestType == RequestConstant.CMD_LS) {
                OutputStream out = s.getOutputStream();
                Ls ls = new Ls();
                LsNode lsNode = ls.getSimpleLsNode();
                byte[] outBytes = lsNode.toBytes();
                out.write(outBytes);
            } else if (requestType == RequestConstant.CMD_GET_FILE) {

            } else if (requestType == RequestConstant.CMD_STOPSERVER) {
                // 停止服务器
                result.setStatus(ResponseConstant.ACTION_RESULT_STOP_SERVER);
            }
        } catch (Exception e) {

        }


        return result;
    }
}
