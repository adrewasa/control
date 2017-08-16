package com.asa.computer.transfer.client;

/**
 * Created by andrew_asa on 2017/7/27.
 */
public class RequestActionResult {

    Object response;

    /**
     * 状态
     */
    private short status;

    /**
     * 信息
     */
    private String message;

    public RequestActionResult(short status) {

        this.status = status;
    }

    public short getStatus() {

        return status;
    }

    public void setStatus(short status) {

        this.status = status;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public void setResponse(Object response) {

        this.response = response;
    }

    public Object getResponse() {

        return response;
    }

    public boolean requestSuccess() {

        return status == RequestConstant.ACTION_RESULT_SUCCESS;
    }
}
