package com.asa.computer.transfer.server.promise.imp;

import com.asa.base.applet.ls.Ls;
import com.asa.base.applet.ls.LsNode;
import com.asa.base.log.LoggerUtils;
import com.asa.base.utils.data.GeneralUtils;
import com.asa.computer.transfer.Constant;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestBody;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;
import com.asa.computer.transfer.server.ResponseActionResult;
import com.asa.computer.transfer.server.ResponseConstant;
import com.asa.computer.transfer.server.Server;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by andrew_asa on 2017/8/17.
 */
public class LsResponseAction extends AbstractResponseAction {

    @Override
    public short getCmd() {

        return RequestConstant.CMD_LS;
    }

    @Override
    public ResponseActionResult actionRequest(Server server, Request request, Socket s) {

        ResponseActionResult ret = new ResponseActionResult(ResponseConstant.ACTION_RESULT_FAIL);
        if (GeneralUtils.allNotNull(request, s) && !s.isClosed()) {
            try {
                RequestHeader header = request.getHeader();
                RequestBody body = request.getBody();
                byte[] bodyData = body.toBytes();
                OutputStream out = s.getOutputStream();
                // 列出文件列表
                String path = Constant.getTransportBasePath();
                if (header.getBodyLen() > 0) {
                    String t = new String(bodyData, 0, header.getBodyLen(), "utf-8");
                    if (basePathCheck(t)) {
                        path = t;
                    }
                }
                Ls ls = new Ls(path);
                LsNode lsNode = ls.getSimpleLsNode();
                byte[] outBytes = lsNode.toBytes();
                out.write(outBytes);
                out.flush();
                out.close();
                LoggerUtils.getLogger(this.getClass()).info("ls {}, info size　{} byte", path, outBytes.length);
                ret.setStatus(ResponseConstant.ACTION_RESULT_SUCCESS);
            } catch (Exception e) {
                ret.setMsg("error in write response");
            }
        }
        return ret;
    }


}
