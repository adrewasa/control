package com.asa.computer.transfer.server;

import com.asa.computer.transfer.Transport;

/**
 * Created by andrew_asa on 2017/7/24.
 */
public class ResponseBody implements Transport {

    /**
     * 列出的目录名 cmd-list-file
     */
    private String dirName;

    @Override
    public int parse(byte[] bytes, int start, int len) {

        return 0;
    }

    @Override
    public byte[] toBytes() {

        return new byte[0];
    }
}
