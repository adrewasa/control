package com.asa.computer.transfer.client;

import com.asa.base.log.LoggerUtils;
import com.asa.computer.transfer.Constant;
import org.slf4j.Logger;

/**
 * Created by andrew_asa on 2017/7/23.
 */
public class Client {

    private Logger LOGGER = LoggerUtils.getLogger(Client.class);

    public Client() {

    }

    public RequestActionResult actionCmd(short cmd, Object... args) {

        try {
            Request request = RequestFactory.getRequest(cmd, args);
            return RequestFactory.actionRequest(cmd, request, Constant.getServerIp(), Constant.getServerPort());
        } catch (Exception e) {
            RequestActionResult error = new RequestActionResult(RequestConstant.CONSTRUCTOR_REQUEST_ERROR);
            error.setMessage(e.getMessage());
            error.setResponse(e);
            LOGGER.info("error action", e);
            return error;
        }
    }

    public static void main(String[] args) {

        Client c = new Client();
    }
}
