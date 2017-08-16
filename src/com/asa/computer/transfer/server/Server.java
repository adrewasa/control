package com.asa.computer.transfer.server;

import com.asa.computer.transfer.Constant;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestFactory;
import com.asa.utils.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by andrew_asa on 2017/7/23.
 */
public class Server implements Runnable {


    public static int STOPPORT = 8889;

    private boolean stop = false;

    @Override
    public void run() {

        ServerSocket stopServer = null;
        try {
            InetAddress ip = InetAddress.getByName(Constant.LOCALHOSTADDR);
            stopServer = new ServerSocket(Constant.SERVERPORT, 5, ip);
            while (!stop) {
                Socket socket = stopServer.accept();
                initRequest(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭服务");
            IOUtils.closeQuietly(stopServer);
        }

    }

    public void initRequest(Socket socket) {

        try {
            InetAddress address = socket.getInetAddress();
            if (verifiyIdentity(address, socket)) {
                InputStream in = socket.getInputStream();
                byte[] buf = new byte[Constant.DATABUFLEN];
                int dataLen;
                dataLen = in.read(buf);
                if (dataLen > 0) {
                    Request request = RequestFactory.getRequest(buf, 0, dataLen);
                    // 返回零说明解析成功
                    if (!request.isBroken()) {
                        dealWithRequest(request, socket);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dealWithRequest(Request request, Socket s) throws Exception {

        ResponseActionResult r = RequestActionFactory.actionRequest(request, s);
        if (r.getStatus() == ResponseConstant.ACTION_RESULT_STOP_SERVER) {
            stop = true;
        }
    }


    /**
     * 校验身份
     *
     * @return
     */
    private boolean verifiyIdentity(InetAddress ip, Socket s) {

        return true;
    }

    private boolean beforeStop() {

        return true;
    }

    public void stop() {

        System.out.println("server stop now");
        beforeStop();
        stop = true;
    }

    public void start() {

        System.out.println("server start now");
        run();
    }

    public static void main(String[] args) {

        Server s = new Server();
        s.run();
    }

}
