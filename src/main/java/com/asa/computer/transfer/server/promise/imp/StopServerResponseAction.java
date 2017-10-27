package com.asa.computer.transfer.server.promise.imp;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.server.ResponseActionResult;
import com.asa.computer.transfer.server.ResponseConstant;
import com.asa.computer.transfer.server.Server;

import java.net.Socket;

/**
 * Created by andrew_asa on 2017/8/17.
 * 停止服务器
 */
public class StopServerResponseAction extends AbstractResponseAction {

    @Override
    public short getCmd() {

        return RequestConstant.CMD_STOPSERVER;
    }

    @Override
    public ResponseActionResult actionRequest(Server server, Request request, Socket s) {

        ResponseActionResult ret = new ResponseActionResult(ResponseConstant.ACTION_RESULT_SUCCESS);
        if (server != null) {
            if (server.isStop()) {
                ret.setMsg("server already stop");
            } else {
                server.stop();
            }
        } else {
            ret.setMsg("server is null");
        }
        return ret;
    }
}
