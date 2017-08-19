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
     * 本地地址
     */
    public static String LOCALHOSTADDR = "127.0.0.1";

    /**
     * 数据缓冲区大小
     */
    public static int DATABUFLEN = 1024 * 100;

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
}
