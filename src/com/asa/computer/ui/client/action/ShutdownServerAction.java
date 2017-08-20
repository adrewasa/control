package com.asa.computer.ui.client.action;

import com.asa.computer.ui.client.ClientUi;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by asa on 2017/8/20.
 * 服务器关机
 */
public class ShutdownServerAction implements ActionListener {

    private ClientUi clientUi;

    public ShutdownServerAction(ClientUi clientUi) {

        this.clientUi = clientUi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获得时间日期模型
        SpinnerDateModel model = new SpinnerDateModel();
        //获得JSPinner对象
        JSpinner year = new JSpinner(model);
        year.setValue(new Date());
        //设置时间格式
        JSpinner.DateEditor editor = new JSpinner.DateEditor(year,
                "yyyy-MM-dd HH:mm:ss");
        year.setEditor(editor);
        year.setBounds(34, 67, 219, 22);
        clientUi.clearContainer();
        clientUi.getjFrame().getContentPane().add(year);
    }
}
