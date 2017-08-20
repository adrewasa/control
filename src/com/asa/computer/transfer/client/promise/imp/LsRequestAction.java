package com.asa.computer.transfer.client.promise.imp;

import com.asa.computer.transfer.Constant;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestBody;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.transfer.client.RequestHeader;
import com.asa.utils.applet.ls.LsNode;
import com.asa.utils.data.bytes.BytesBuffer;
import com.asa.utils.io.IOUtils;
import com.asa.utils.log.LoggerUtils;
import org.slf4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by andrew_asa on 2017/8/16.
 * ls 服务器目录
 */
public class LsRequestAction extends AbstractRequestAction {

    private static Logger LOGGER = LoggerUtils.getLogger(LsRequestAction.class);

    @Override
    public short getCmd() {

        return RequestConstant.CMD_LS;
    }

    @Override
    public Request getRequest(Object... args) throws UnsupportedEncodingException {

        Request ret = new Request();
        RequestHeader header = ret.getHeader();
        RequestBody body = ret.getBody();
        header.setCmd(getCmd());
        // 如果是获取目录,第一个参数为目录的名字,默认为"./"
        if (args.length > 0) {
            // 如果有参数则第一个参数就是的路径
            String dir = (String) args[0];
            body.append(dir.getBytes("utf-8"));
            header.setBodyLen(body.length());
        } else {
            header.setBodyLen(0);
        }
        return ret;
    }

    @Override
    public RequestActionResult actionRequest(short cmd, Request request, String ip, int port) {

        RequestActionResult ret = new RequestActionResult(RequestConstant.ACTION_RESULT_FAIL);
        if (request != null) {
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            try {
                socket = new Socket(ip, port);
                out = socket.getOutputStream();
                out.write(request.toBytes());
                out.flush();
                in = socket.getInputStream();
                // 总长度
                long totalLen = 0;
                BytesBuffer bf = new BytesBuffer();
                byte[] rev = new byte[1024];
                int revLen;
                boolean shouldParse = true;
                while ((revLen = in.read(rev)) != -1) {
                    totalLen+= revLen;
                    bf.append(rev, 0, revLen);
                    if (totalLen > Constant.MAX_LS_DATA) {
                        LOGGER.info("ls data out of bound");
                        shouldParse = false;
                        break;
                    }
                }
                if (shouldParse) {
                    LoggerUtils.getLogger(this.getClass()).info("rev ls {} byte data", totalLen);
                    LsNode lsNode = new LsNode();
                    int r = lsNode.parse(bf.getBytes(), 0, (int)totalLen);
                    if (r == 0) {
                        ret.setResponse(lsNode);
                        ret.setStatus(RequestConstant.ACTION_RESULT_SUCCESS);
                    }
                }else {
                    ret.setMessage("ls data out of bound");
                }
            } catch (UnknownHostException e) {
                ret.setMessage("UnknownHost");
                LOGGER.info("UnknownHost ip={},port={}", ip, port);
            } catch (ConnectException e){
                ret.setMessage("time out connection server");
            } catch(Exception e) {
                ret.setMessage(e.getMessage());
            } finally {
                IOUtils.closeQuietly(in, out, socket);
            }
        } else {
            // 构建请求的时候就出错了
            ret.setMessage("error in build request");
        }
        return ret;
    }
}
