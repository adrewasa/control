package com.asa.computer.transfer.client;

import com.asa.computer.transfer.Constant;

/**
 * Created by andrew_asa on 2017/7/23.
 */
public class Client {

    public Client() {

    }

    public RequestActionResult actionCmd(short cmd, Object... args) {

        Request request = RequestFactory.getRequest(cmd, args);
        return RequestFactory.actionRequest(cmd, request, Constant.getServerIp(), Constant.getServerPort());
    }

    public static void main(String[] args) {

        Client c = new Client();
    }
}
