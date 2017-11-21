package com.asa.computer.transfer.server.promise.imp;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.server.ResponseActionResult;
import com.asa.computer.transfer.server.Server;
import java.net.Socket;

/**
 * @author andrew.asa
 * @create 2017-11-17
 **/
public class GetDirResponseAction extends BaseGetFileResponseAction {


    @Override
    public short getCmd() {

        return RequestConstant.CMD_GET_DIR;
    }

    @Override
    public ResponseActionResult actionRequest(Server server, Request request, Socket s) {

        return null;
    }
}
