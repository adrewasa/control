package com.asa.computer.ui.client.action;

import com.asa.computer.ui.client.ClientUi;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
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

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        //Date date = new Date();

        //calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
        //calendar.set(Calendar.MINUTE, 1);
        //calendar.set(Calendar.SECOND, 1);
        //calendar = new Calendar() {
        //
        //}

        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());

        JSpinner spinner = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);

        spinner.setEditor(editor);

        JPanel content = new JPanel();
        content.add(spinner);

        JFrame frame = new JFrame("Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(content);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
