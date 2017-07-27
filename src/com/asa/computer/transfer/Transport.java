package com.asa.computer.transfer;

/**
 * Created by andrew_asa on 2017/7/25.
 */
public interface Transport {

    /**
     * 对数据进行解析
     *
     * @param bytes
     */
    int parse(byte[] bytes, int start, int len);

    /**
     * 转换成传输的字节
     *
     * @return
     */
    byte[] toBytes();

}
