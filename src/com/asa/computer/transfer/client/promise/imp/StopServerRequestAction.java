package com.asa.computer.transfer.client.promise.imp;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;

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

        return null;
    }
}
