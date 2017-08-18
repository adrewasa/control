package com.asa.computer.transfer.server.promise.imp.data;

import com.asa.computer.transfer.Transport;
import com.asa.utils.data.ComparatorUtils;
import com.asa.utils.data.bytes.BytesBuffer;
import com.asa.utils.data.bytes.BytesUtils;

/**
 * Created by andrew_asa on 2017/8/17.
 */
public class GetFileResponse implements Transport {

    /**
     * 结果类型
     */
    private short type;

    /**
     * 没有该文件
     */
    public static short RESPONSE_TYPE_NULL = -1;

    /**
     * 没有该文件
     */
    public static short RESPONSE_TYPE_HAVE_NO_FILE = 0;

    /**
     * 没有权限
     */
    public static short RESPONSE_TYPE_NO_POWER = 1;

    /**
     * 开始传输
     */
    public static short RESPONSE_TYPE_START_TRANSPORT = 2;

    public static short RESPONSE_TYPE_START_RECEIVE = 3;

    private GetFileResponse(short type) {

        this.type = type;
    }

    @Override
    public int parse(byte[] bytes, int start, int len) {

        if (bytes == null) {
            return -1;
        }
        if (start + 2 > len) {
            return start;
        }
        type = BytesUtils.byteArrayToShort(bytes, start, 2);
        return 0;
    }

    @Override
    public byte[] toBytes() {

        BytesBuffer bf = new BytesBuffer();
        bf.append(type);
        return bf.getBytes();
    }

    /**
     * 是否可以传输文件
     *
     * @return
     */
    public boolean canGetFile() {

        return ComparatorUtils.isEqual(type, RESPONSE_TYPE_START_TRANSPORT);
    }

    public boolean startReveive() {

        return ComparatorUtils.isEqual(type, RESPONSE_TYPE_START_RECEIVE);
    }

    /**
     * 获取响应报文
     *
     * @param type
     * @return
     */
    public static GetFileResponse getGetFileResponse(short type) {

        return new GetFileResponse(type);
    }

    public String getDescription() {

        if (ComparatorUtils.isEqual(type, RESPONSE_TYPE_HAVE_NO_FILE)) {
            return "没有该文件";
        } else if (ComparatorUtils.isEqual(type, RESPONSE_TYPE_NO_POWER)) {
            return "没有权限";
        } else if (ComparatorUtils.isEqual(type, RESPONSE_TYPE_START_TRANSPORT)) {
            return "开始传输";
        } else if (ComparatorUtils.isEqual(type, RESPONSE_TYPE_START_RECEIVE)) {
            return "开始接受";
        }
        return "不知名类型";
    }

}
