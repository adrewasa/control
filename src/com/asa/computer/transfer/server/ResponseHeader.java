package com.asa.computer.transfer.server;

import com.asa.computer.transfer.Transport;
import com.asa.utils.data.bytes.BytesBuffer;

/**
 * Created by andrew_asa on 2017/7/24.
 */
public class ResponseHeader extends BytesBuffer implements Transport {


    /**
     * body长度
     */
    private int bodyLen;

    public int getBodyLen() {

        return bodyLen;
    }

    @Override
    public int parse(byte[] bytes, int start, int len) {

        return 0;
    }

    @Override
    public byte[] toBytes() {

        return new byte[0];
    }
}
