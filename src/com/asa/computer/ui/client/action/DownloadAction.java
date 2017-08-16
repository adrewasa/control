package com.asa.computer.ui.client.action;

import com.asa.computer.transfer.client.Client;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.utils.applet.ls.LsNode;
import com.asa.utils.data.GeneralUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andrew_asa on 2017/8/16.
 * 文件下载
 */
public class DownloadAction implements ActionListener {

    Client client;

    LsNode node;

    public DownloadAction(Client client, LsNode node) {

        this.client = client;
        this.node = node;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // 参数不为空的时候才进行文件下载
        if (GeneralUtils.allNotNull(client, node)) {
            client.actionCmd(RequestConstant.CMD_GET_FILE, node.getName());
        }
    }
}
