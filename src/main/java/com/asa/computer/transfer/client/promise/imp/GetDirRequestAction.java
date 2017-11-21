package com.asa.computer.transfer.client.promise.imp;

import com.asa.base.log.LoggerUtils;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestConstant;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;

/**
 * @author andrew.asa
 * @create 2017-11-17
 **/
public class GetDirRequestAction extends BaseGetFileAction {

    Logger LOGGER = LoggerUtils.getLogger(GetDirRequestAction.class);


    @Override
    public short getCmd() {

        return RequestConstant.CMD_GET_DIR;
    }

    @Override
    void receiveFromServer(Request request, InputStream in, OutputStream out, RequestActionResult ret) throws Exception {

    }
}
