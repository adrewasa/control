package com.asa.computer.transfer.server;

import com.asa.computer.transfer.Transport;

/**
 * Created by andrew_asa on 2017/7/24.
 */
public interface ResponseBody extends Transport {

    /**
     * 文件大小
     * @return
     */
    int size();
}
