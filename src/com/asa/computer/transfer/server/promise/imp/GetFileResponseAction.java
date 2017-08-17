package com.asa.computer.transfer.server.promise.imp;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.server.ResponseActionResult;
import com.asa.computer.transfer.server.Server;

import java.net.Socket;

/**
 * Created by andrew_asa on 2017/8/16.
 * 获取文件响应动作
 */
public class GetFileResponseAction extends AbstractResponseAction{

    @Override
    public short getCmd() {

        return RequestConstant.CMD_GET_FILE;
    }

    @Override
    public ResponseActionResult actionRequest(Server server, Request request, Socket s) {

        return null;
    }
}
