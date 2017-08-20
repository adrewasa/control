package com.asa.computer.ui.server.action;

import com.asa.computer.ui.server.ServerUi;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asa on 2017/8/20.
 * 服务器端传输相关设置
 */
public class ServerTransportSetting implements ActionListener {

    private JFrame jFrame;

    private ServerUi serverUi;

    public ServerTransportSetting(JFrame jFrame, ServerUi serverUi) {

        this.jFrame = jFrame;
        this.serverUi = serverUi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * 显示上一次设定之前需要先进行清除
     */
    private void beforeShow() {

        serverUi.clearContain();
    }
}
