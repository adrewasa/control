package com.asa.computer.transfer.server.promise.imp;

import com.asa.computer.transfer.Constant;
import com.asa.computer.transfer.server.promise.ResponseAction;

/**
 * Created by andrew_asa on 2017/8/17.
 */
public abstract class AbstractResponseAction implements ResponseAction {

    /**
     * 文件路径是否合法
     *
     * @param p
     * @return
     */
    protected boolean basePathCheck(String p) {

        return p != null && p.startsWith(Constant.TRANSPORTBASEPATH) && !p.contains("..");
    }
}
