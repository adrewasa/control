package com.asa.computer.transfer.client;

/**
 * Created by andrew_asa on 2017/7/23.
 */
public class RequestConstant {

    /**
     * 远程ls 命令
     */
    public static short CMD_LS = 1;

    /**
     * 获取文件
     */
    public static short CMD_GET_FILE = 2;

    /**
     * 登录
     */
    public static short CMD_LOGIN = 3;

    /**
     * 退出登录
     */
    public static short CMD_SINGOUT = 4;

    /**
     * 更新服务器
     */
    public static short CMD_UPDATA_SERVER = 5;

    /**
     * 停止服务器
     */
    public static short CMD_STOPSERVER = 5;

    public static short cmdNameTocmd(String cmd) {

        if ("CMD_LS".equalsIgnoreCase(cmd)) {
            return CMD_LS;
        } else if ("CMD_GET_FILE".equalsIgnoreCase(cmd)) {
            return CMD_GET_FILE;
        } else if ("CMD_LOGIN".equalsIgnoreCase(cmd)) {
            return CMD_LOGIN;
        } else if ("CMD_SINGOUT".equalsIgnoreCase(cmd)) {
            return CMD_SINGOUT;
        } else if ("CMD_SINGOUT".equalsIgnoreCase(cmd)) {
            return CMD_STOPSERVER;
        }
        return CMD_LS;
    }


    /**
     * 响应结果成功
     */
    public static short ACTION_RESULT_SUCCESS = 0;

    /**
     * 响应失败
     */
    public static short ACTION_RESULT_FAIL = 1;

    /**
     * 停止服务器响应
     */
    public static short ACTION_RESULT_STOP_SERVER = 2;
}
