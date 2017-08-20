package com.asa.computer.transfer.server.promise.imp.data;

import com.asa.computer.transfer.Transport;
import com.asa.computer.transfer.server.ResponseBody;
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
     * body长度
     */
    private int bodyLen;

    /**
     * 回应body
     */
    private ResponseBody body;

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

    /**
     * 开始接收
     */
    public static short RESPONSE_TYPE_START_RECEIVE = 3;

    /**
     * 解析错误
     */
    public static short RESPONSE_TYPE_ERROR_PARSE = 4;

    private GetFileResponse(short type, Object... args) {

        this.type = type;
        this.body = getBodyFromType(type, args);
    }

    @Override
    public int parse(byte[] bytes, int start, int len) {

        int offset = start;
        if (bytes == null) {
            return -1;
        }
        if (offset + 2 > len) {
            return offset;
        }
        type = BytesUtils.byteArrayToShort(bytes, offset, 2);
        offset += 2;
        if (body.parse(bytes, offset, len) != 0) {
            type = RESPONSE_TYPE_ERROR_PARSE;
            return -1;
        }
        return 0;
    }

    @Override
    public byte[] toBytes() {

        BytesBuffer bf = new BytesBuffer();
        bf.append(type);
        bf.append(body.toBytes());
        return bf.getBytes();
    }

    public ResponseBody getBodyFromType(short type, Object... args) {

        if (ComparatorUtils.isEqual(type, RESPONSE_TYPE_START_TRANSPORT)) {
            StartTransportBody body = new StartTransportBody();
            // 需要设置文件的大小
            if (args.length > 0) {
                body.setFileLen((long) args[0]);
            }
            return body;
        }
        return new NoneBodyResponse();
    }

    public ResponseBody getBody() {

        return body;
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
    public static GetFileResponse getGetFileResponse(short type, Object... args) {

        return new GetFileResponse(type, args);
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
        } else if (ComparatorUtils.isEqual(type, RESPONSE_TYPE_ERROR_PARSE)) {
            return "解析错误";
        }
        return "不知名类型";
    }

    public void setBodyLen(int bodyLen) {

        this.bodyLen = bodyLen;
    }

    public void setBody(ResponseBody body) {

        this.body = body;
    }

    public short getType() {

        return type;
    }

    public int getBodyLen() {

        return bodyLen;
    }

    public ResponseBody getBodyFromType() {

        return body;
    }

    /**
     * 存放传输文件的大小MdD5(todo)等信息
     */
    public class StartTransportBody implements ResponseBody {

        private long fileLen;

        @Override
        public int parse(byte[] bytes, int start, int len) {

            if (start + 4 > len) {
                return start;
            }
            fileLen = BytesUtils.byteArrayToLong(bytes, start, 8);
            return 0;
        }

        @Override
        public byte[] toBytes() {

            BytesBuffer bf = new BytesBuffer();
            bf.append(fileLen);
            return bf.getBytes();
        }

        @Override
        public int size() {

            return toBytes().length;
        }

        public void setFileLen(long fileLen) {

            this.fileLen = fileLen;
        }

        public long getFileLen() {

            return fileLen;
        }
    }

    /**
     * 表示没有
     */
    public class NoneBodyResponse implements ResponseBody {

        @Override
        public int parse(byte[] bytes, int start, int len) {

            return 0;
        }

        @Override
        public byte[] toBytes() {

            return new byte[0];
        }

        @Override
        public int size() {

            return 0;
        }
    }

}
