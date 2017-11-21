package com.asa.computer.transfer.client.promise;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;

/**
 * Created by andrew_asa on 2017/8/16.
 */
public interface RequestAction {

    /**
     * 获取命令
     *
     * @return
     */
    short getCmd();

    /**
     * 构造请求数据包
     *
     * @return
     */
    Request getRequest(Object... args) throws Exception;

    /**
     * 执行请求动作
     *
     * @param cmd
     * @param request
     * @param ip
     * @param port
     * @return
     */
    RequestActionResult actionRequest(short cmd,Request request, String ip, int port);
}
