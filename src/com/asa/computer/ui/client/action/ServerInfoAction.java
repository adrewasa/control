package com.asa.computer.ui.client.action;

import com.asa.computer.ui.client.ClientUi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 服务器信息
 */
public class ServerInfoAction implements ActionListener {

    ClientUi clientUi ;

    public ServerInfoAction(ClientUi clientUi) {
        this.clientUi = clientUi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        beforePaint();
        initPanel();
    }

    private void beforePaint() {
        clientUi.clearContainer();
    }

    private void initPanel() {

    }
}
