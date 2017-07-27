package com.asa.computer.transfer.client;

import com.asa.computer.transfer.Transport;
import com.asa.utils.data.bytes.BytesBuffer;

/**
 * Created by andrew_asa on 2017/7/24.
 */
public class RequestBody extends BytesBuffer implements Transport {

    @Override
    public int parse(byte[] bytes, int start, int len) {

        return 0;
    }

    @Override
    public byte[] toBytes() {

        return getBytes();
    }
}
