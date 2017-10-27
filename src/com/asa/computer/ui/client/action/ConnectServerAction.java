package com.asa.computer.ui.client.action;

import com.asa.base.applet.btscan.NetBiosResponse;
import com.asa.base.log.LoggerUtils;
import com.asa.computer.transfer.Constant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asa on 2017/8/20.
 * 连接服务器
 */
public class ConnectServerAction implements ActionListener {

    private NetBiosResponse node;

    private FindServerAction findServerAction;

    public ConnectServerAction(NetBiosResponse node, FindServerAction findServerAction) {

        this.node = node;
        this.findServerAction = findServerAction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LoggerUtils.getLogger(this.getClass()).info("try to connect server,ip {}", node.getLocalStrIp());
        // TODO 建立连接的过程仍然需要进一步完善
        Constant.setServerIp(node.getLocalStrIp());
    }
}
