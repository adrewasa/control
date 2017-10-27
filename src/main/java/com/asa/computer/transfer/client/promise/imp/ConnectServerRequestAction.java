package com.asa.computer.transfer.client.promise.imp;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;

import java.io.UnsupportedEncodingException;

/**
 * Created by asa on 2017/8/20.
 * 服务器是否已经开启 用于扫描之后的尝试连接
 * 主要是采用尝试连接的，以及发送特定的指令进行连接
 */
public class ConnectServerRequestAction extends AbstractRequestAction {

    @Override
    public short getCmd() {

        return RequestConstant.CMD_SERVER_START;
    }

    @Override
    public Request getRequest(Object... args) throws UnsupportedEncodingException {

        Request ret = new Request();
        RequestHeader header = ret.getHeader();
        header.setCmd(getCmd());
        header.setBodyLen(0);
        return ret;
    }

    @Override
    public RequestActionResult actionRequest(short cmd, Request request, String ip, int port) {

        return null;
    }
}
