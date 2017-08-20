package com.asa.computer.transfer.client;

import com.asa.computer.transfer.Constant;

/**
 * Created by andrew_asa on 2017/7/23.
 */
public class Client {

    private String serverIp = "192.168.1.101";

    private int serverPort = Constant.SERVERPORT;

    public Client() {

    }

    public Client(short cmd) {

    }

    public Client(String ip, int port) {

        this.serverIp = ip;
        this.serverPort = port;
    }

    public RequestActionResult actionCmd(short cmd, Object... args) {

        Request request = RequestFactory.getRequest(cmd, args);
        return RequestFactory.actionRequest(cmd,request, serverIp, serverPort);
    }

    public static void main(String[] args) {

        Client c = new Client();
    }
}
