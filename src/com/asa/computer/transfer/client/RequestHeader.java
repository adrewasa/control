package com.asa.computer.transfer.client;

import com.asa.base.utils.data.bytes.BytesBuffer;
import com.asa.base.utils.data.bytes.BytesUtils;
import com.asa.computer.transfer.Transport;

/**
 * Created by andrew_asa on 2017/7/24.
 * 请求头
 */
public class RequestHeader implements Transport {

    /**
     * 命令
     */
    private short cmd = 0;

    /**
     * body的长度
     */
    private int bodyLen = 0;

    public void setBodyLen(int bodyLen) {

        this.bodyLen = bodyLen;
    }

    public int getBodyLen() {

        return bodyLen;
    }

    public void setCmd(short cmd) {

        this.cmd = cmd;
    }

    public short getCmd() {

        return cmd;
    }

    public byte[] toBytes() {

        BytesBuffer bf = new BytesBuffer();
        bf.append(cmd);
        bf.append(bodyLen);
        return bf.getBytes();
    }

    @Override
    public int parse(byte[] bytes, int start, int len) {

        int offset = start;
        if (start + 2 > len) {
            return offset;
        }
        cmd = BytesUtils.byteArrayToShort(bytes, offset, 2);
        offset += 2;

        if (offset + 4 > len) {
            return offset;
        }
        bodyLen = BytesUtils.byteArrayToInt(bytes, offset, 4);
        return 0;
    }

    /**
     *
     * @return
     */
    public int size() {

        return toBytes().length;
    }
}
