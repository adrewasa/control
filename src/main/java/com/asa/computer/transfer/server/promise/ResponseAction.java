package com.asa.computer.transfer.server.promise;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.server.ResponseActionResult;
import com.asa.computer.transfer.server.Server;

import java.net.Socket;

/**
 * Created by andrew_asa on 2017/8/16.
 */
public interface ResponseAction {

    /**
     * 获取命令
     *
     * @return
     */
    short getCmd();

    /**
     * 响应请求
     * @param request
     * @param s
     * @return
     */
    ResponseActionResult actionRequest(Server server,Request request, Socket s);
}
