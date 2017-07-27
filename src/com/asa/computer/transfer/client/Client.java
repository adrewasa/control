package com.asa.computer.transfer.client;

/**
 * Created by andrew_asa on 2017/7/23.
 */
public class Client {


    public Client() {


    }

    public Client(short cmd) {

        Request request = RequestFactory.getRequst(cmd);
        RequestFactory.actionRequest(request);
    }

    public static void main(String[] args) {

        Client c = new Client();
    }
}
