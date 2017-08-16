package com.asa.computer.transfer.client;

import com.asa.computer.transfer.client.promise.RequestAction;
import com.asa.computer.transfer.client.promise.imp.GetFileRequestAction;
import com.asa.computer.transfer.client.promise.imp.LsRequestAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrew_asa on 2017/7/26.
 */
public class RequestFactory {

    /**
     * 动作容器
     */
    private static Map<Short, RequestAction> actionMap = new HashMap<Short, RequestAction>();

    private static boolean isInit = false;

    /**
     * 构造一个请求的命令
     *
     * @param cmd  命令类型
     * @param args 参数
     * @return
     */
    public static Request getRequest(short cmd, Object... args) {

        Short key = new Short(cmd);
        RequestAction action = actionMap.get(key);
        if (action != null) {
            return action.getRequest(args);
        }
        return null;
    }

    /**
     * 根据返回的byte数据解析成为一个request
     *
     * @param bytes
     * @param start
     * @param len
     * @return
     */
    public static Request getRequest(byte[] bytes, int start, int len) {

        int offset = start;
        Request ret = new Request();
        RequestHeader header = ret.getHeader();
        RequestBody body = ret.getBody();
        int h = header.parse(bytes, start, len);
        if (h != 0) {
            ret.setBroken(offset);
            return ret;
        }
        offset += header.size();
        int b = body.parse(bytes, offset, len);
        if (b != 0) {
            ret.setBroken(offset);
            return ret;
        }
        return ret;
    }

    public static RequestActionResult actionRequest(short cmd, Request request, String ip, int port) {

        Short key = new Short(cmd);
        RequestAction action = actionMap.get(key);
        return action.actionRequest(cmd, request, ip, port);
    }

    public static RequestAction getAction(short cmd) {

        return actionMap.get(new Short(cmd));
    }

    /**
     * 动作注册
     *
     * @param action
     */
    public static void registeAction(RequestAction action) {

        if (action != null) {
            if (!actionMap.containsKey(new Short(action.getCmd()))) {
                actionMap.put(new Short(action.getCmd()), action);
            }
        }
    }

    /**
     * TODO 需要移到启动项里面
     */
    static {
        if (!isInit) {
            isInit = true;
            registeAction(new LsRequestAction());
            registeAction(new GetFileRequestAction());
        }
    }
}
