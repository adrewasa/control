package com.asa.computer.transfer.server.promise.imp;

import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestBody;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;
import com.asa.computer.transfer.server.ResponseActionResult;
import com.asa.computer.transfer.server.ResponseConstant;
import com.asa.computer.transfer.server.Server;
import com.asa.computer.transfer.server.promise.imp.data.GetFileResponse;
import com.asa.utils.data.GeneralUtils;
import com.asa.utils.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by andrew_asa on 2017/8/16.
 * 获取文件响应动作
 */
public class GetFileResponseAction extends AbstractResponseAction {

    @Override
    public short getCmd() {

        return RequestConstant.CMD_GET_FILE;
    }

    @Override
    public ResponseActionResult actionRequest(Server server, Request request, Socket s) {

        ResponseActionResult ret = new ResponseActionResult(ResponseConstant.ACTION_RESULT_FAIL);
        if (GeneralUtils.allNotNull(request, s) && !s.isClosed()) {
            OutputStream out = null;
            InputStream in = null;
            try {
                RequestHeader header = request.getHeader();
                RequestBody body = request.getBody();
                byte[] bodyData = body.toBytes();
                out = s.getOutputStream();
                // 列出文件列表
                if (header.getBodyLen() > 0) {
                    String t = new String(bodyData, 0, header.getBodyLen());
                    GetFileResponse response = null;
                    File send;
                    while (true) {
                        if (!basePathCheck(t)) {
                            // 文件没有权限
                            response = GetFileResponse.getGetFileResponse(GetFileResponse.RESPONSE_TYPE_NO_POWER);
                            ret.setMsg("unsafe file name:" + t);
                            break;
                        }
                        send = new File(t);
                        // 文件不存在
                        if (!send.exists()) {
                            response = GetFileResponse.getGetFileResponse(GetFileResponse.RESPONSE_TYPE_HAVE_NO_FILE);
                            ret.setMsg("file not exit");
                            break;
                        }
                        // 开始传输
                        response = GetFileResponse.getGetFileResponse(GetFileResponse.RESPONSE_TYPE_START_TRANSPORT);
                        break;
                    }
                    // 传输会送信息
                    out.write(response.toBytes());
                    // 强制刷新一下以便等一下传输文件
                    out.flush();
                    // 有文件需要进行传输
                    if (response.canGetFile()) {
                        // 需要等待对方确认是否需要传输
                        byte[] rev = new byte[2];
                        in = s.getInputStream();
                        int revLen = in.read(rev);
                        GetFileResponse r = GetFileResponse.getGetFileResponse(GetFileResponse.RESPONSE_TYPE_NULL);
                        r.parse(rev, 0, revLen);
                        if (r.startReveive()) {
                            // 已经准备好接收文件了 开始写文件

                        } else {

                        }
                    }
                } else {
                    ret.setMsg("have not fileName");
                }
            } catch (Exception e) {
                ret.setMsg("error in sent file");
            } finally {
                IOUtils.closeQuietly(s, out, in);
            }
        }
        return ret;
    }

}
