package com.asa.computer.transfer.client.promise.imp;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;

import java.io.UnsupportedEncodingException;

/**
 * Created by asa on 2017/8/20.
 * 服务器关机
 */
public class ShutdownServerRequestAction extends AbstractRequestAction {

    @Override
    public short getCmd() {

        return 0;
    }

    @Override
    public Request getRequest(Object... args) throws UnsupportedEncodingException {

        return null;
    }

    @Override
    public RequestActionResult actionRequest(short cmd, Request request, String ip, int port) {

        return null;
    }
}
