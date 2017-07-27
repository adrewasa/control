package com.asa.computer.transfer.client;

import com.asa.computer.transfer.Transport;
import com.asa.utils.data.bytes.BytesBuffer;

/**
 * Created by andrew_asa on 2017/7/23.
 */
public class Request implements Transport {


    private RequestHeader header;

    private RequestBody body;

    /**
     * 断点
     */
    private int broken;

    public Request() {

        header = new RequestHeader();
        body = new RequestBody();
    }


    public void printf() {
        //System.out.println("cmd:%s",cmd);
    }

    public RequestHeader getHeader() {

        return header;
    }

    public RequestBody getBody() {

        return body;
    }

    public void setHeader(RequestHeader header) {

        this.header = header;
    }

    public void setBody(RequestBody body) {

        this.body = body;
    }

    @Override
    public int parse(byte[] bytes, int start, int len) {

        return 0;
    }

    @Override
    public byte[] toBytes() {

        BytesBuffer bf = new BytesBuffer();
        bf.append(header.toBytes());
        bf.append(body.toBytes());
        return bf.getBytes();
    }

    /**
     * 解析请求给的数据是否是被截断的
     *
     * @return
     */
    public boolean isBroken() {

        return broken > 0;
    }

    public void setBroken(int broken) {

        this.broken = broken;
    }
}
