package com.asa.computer.ui.client.action;

import com.asa.computer.transfer.Constant;
import com.asa.utils.applet.btscan.NetBiosResponse;
import com.asa.utils.log.LoggerUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asa on 2017/8/20.
 * 连接服务器
 */
public class ConnectServerAction implements ActionListener {

    private NetBiosResponse node;

    private FindServer findServer;

    public ConnectServerAction(NetBiosResponse node, FindServer findServer) {

        this.node = node;
        this.findServer = findServer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LoggerUtils.getLogger(this.getClass()).info("try to connect server,ip {}", node.getLocalStrIp());
        // TODO 建立连接的过程仍然需要进一步完善
        Constant.setServerIp(node.getLocalStrIp());
    }
}
