package com.asa.computer.transfer.server;

/**
 * Created by andrew_asa on 2017/7/26.
 */
public class ResponseActionResult {


    private short status;

    private String msg;

    public ResponseActionResult(short status) {

        this.status = status;
    }

    public short getStatus() {

        return status;
    }

    public void setStatus(short status) {

        this.status = status;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }

    public String getMsg() {

        return msg;
    }
}
