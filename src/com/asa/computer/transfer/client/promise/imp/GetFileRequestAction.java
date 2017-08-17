package com.asa.computer.transfer.client.promise.imp;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestBody;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;

/**
 * Created by andrew_asa on 2017/8/16.
 * 获取文件请求
 */
public class GetFileRequestAction extends AbstractRequestAction {

    @Override
    public short getCmd() {

        return RequestConstant.CMD_GET_FILE;
    }

    @Override
    public Request getRequest(Object... args) {

        // 应该给文件路径
        if (args.length < 1) {
            return null;
        }
        Request ret = new Request();
        RequestHeader header = ret.getHeader();
        RequestBody body = ret.getBody();
        header.setCmd(getCmd());
        // 如果有参数则第一个参数就是的路径
        String dir = (String) args[0];
        body.append(dir);
        //
        header.setBodyLen(body.length());
        return ret;
    }

    @Override
    public RequestActionResult actionRequest(short cmd,Request request, String ip, int port) {

        return null;
    }


}
