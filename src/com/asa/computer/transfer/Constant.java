package com.asa.computer.transfer;

import org.apache.commons.lang3.SystemUtils;

/**
 * Created by andrew_asa on 2017/7/26.
 */
public class Constant {

    /**
     * 服务器地址
     */
    public static int SERVERPORT = 8888;

    /**
     * 服务器默认地址
     */
    public static String serverIp = "192.168.1.101";

    /**
     * 本地地址
     */
    public static String LOCALHOSTADDR = "127.0.0.1";

    /**
     * 数据缓冲区大小
     */
    public static int DATABUFLEN = 1024 * 1024;

    /**
     * 限定最大的ls数据 --- 防止别人有意的发送
     */
    public static int MAX_LS_DATA = 1024 * 1024;

    /**
     * 传输基路径
     */
    public static String TRANSPORTBASEPATH = null;

    public static String TRANSPORTREVEIVEPATH = null;

    public static boolean isInit = false;

    static {

        init();
    }

    public synchronized static void init() {

        if (isInit) {
            return;
        }
        isInit = true;
        if (SystemUtils.IS_OS_WINDOWS) {
            TRANSPORTBASEPATH = "F:\\new";
            TRANSPORTREVEIVEPATH = "F:\\download";
            serverIp = "192.168.1.104";
        } else {
            TRANSPORTBASEPATH = "/Users/andrew_asa/Downloads/temp/";
            TRANSPORTREVEIVEPATH = "/Users/andrew_asa/Downloads/downloads/";
        }
    }

    public static String getTransportBasePath() {

        return TRANSPORTBASEPATH;
    }

    public static String getTransportReveivePath() {

        return TRANSPORTREVEIVEPATH;
    }

    public synchronized static void setServerIp(String serverIp) {

        Constant.serverIp = serverIp;
    }

    public synchronized static String getServerIp() {

        return serverIp;
    }

    public synchronized static void setServerPort(int port) {

        Constant.SERVERPORT = port;
    }

    public synchronized static int getServerPort() {

        return SERVERPORT;
    }
}
