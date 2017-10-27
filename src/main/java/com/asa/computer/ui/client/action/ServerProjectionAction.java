package com.asa.computer.ui.client.action;

import com.asa.computer.ui.client.ClientUi;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andrew_asa on 2017/8/23.
 * 服务器桌面投影
 */
public class ServerProjectionAction implements ActionListener {

    JFrame jFrame;

    ClientUi clientUi;

    public ServerProjectionAction(JFrame jFrame, ClientUi clientUi) {

        this.jFrame = jFrame;
        this.clientUi = clientUi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
