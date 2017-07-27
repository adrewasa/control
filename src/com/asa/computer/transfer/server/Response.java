package com.asa.computer.transfer.server;

import com.asa.computer.transfer.Transport;

/**
 * Created by andrew_asa on 2017/7/23.
 */
public class Response implements Transport{


    /**
     * 数据长度
     */
    public int dataLen;

    public short cmd;

    /**
     * 当前分片索引
     */
    public int splitIndex;


    @Override
    public int parse(byte[] bytes, int start, int len) {

        return 0;
    }

    @Override
    public byte[] toBytes() {

        return new byte[0];
    }
}
