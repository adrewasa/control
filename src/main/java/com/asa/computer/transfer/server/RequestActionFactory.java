package com.asa.computer.transfer.server;

import com.asa.computer.transfer.Constant;
import com.asa.computer.transfer.client.Request;
import com.asa.computer.transfer.client.RequestHeader;
import com.asa.computer.transfer.server.promise.ResponseAction;
import com.asa.computer.transfer.server.promise.imp.GetFileResponseAction;
import com.asa.computer.transfer.server.promise.imp.LsResponseAction;
import com.asa.computer.transfer.server.promise.imp.StopServerResponseAction;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrew_asa on 2017/7/26.
 */
public class RequestActionFactory {

    /**
     * 响应动作集合
     */
    private static Map<Short, ResponseAction> actionMap = new HashMap<Short, ResponseAction>();

    private static boolean isInit = false;

    /**
     * 注册 响应动作
     *
     * @param action
     */
    public static void regResponseAction(ResponseAction action) {

        if (action != null) {
            if (actionMap.get(new Short(action.getCmd())) == null) {
                actionMap.put(new Short(action.getCmd()), action);
            }
        }
    }

    public static ResponseAction getResponseAction(Short cmd) {

        return actionMap.get(cmd);
    }


    /**
     * 获取响应动作
     *
     * @param cmd
     * @return
     */
    public static ResponseAction getResponseAction(short cmd) {

        return getResponseAction(new Short(cmd));
    }

    public static ResponseActionResult actionRequest(Server server, Request request, Socket s) {

        RequestHeader header = request.getHeader();
        short requestType = header.getCmd();
        ResponseAction action = getResponseAction(requestType);
        if (action != null) {
            return action.actionRequest(server, request, s);
        } else {
            ResponseActionResult result = new ResponseActionResult(ResponseConstant.ACTION_RESULT_FAIL);
            result.setMsg("not fit action:" + requestType);
            return result;
        }
    }

    private static boolean basePathCheck(String p) {

        return p != null && p.startsWith(Constant.getTransportBasePath()) && !p.contains("..");
    }

    /**
     * TODO 需要移到启动项里面
     */
    static {
        if (!isInit) {
            isInit = true;
            regResponseAction(new LsResponseAction());
            regResponseAction(new GetFileResponseAction());
            regResponseAction(new StopServerResponseAction());
        }
    }
}
