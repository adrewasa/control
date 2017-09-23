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

/**
 * Created by asa on 2017/8/20.
 * 服务器关机
 */
public class ShutdownServerAction implements ActionListener {

    private ClientUi clientUi;

    private JPanel jPanel;

    public ShutdownServerAction(ClientUi clientUi) {

        this.clientUi = clientUi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        clientUi.clearContainer();
        jPanel = new JPanel();
        clientUi.getjFrame().getContentPane().add(jPanel);
        JSpinner jSpinner = getJspinner();
        jPanel.add(jSpinner);
        clientUi.getjFrame().validate();
    }

    private JSpinner getJspinner() {

        Calendar calendar = Calendar.getInstance();
        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());

        JSpinner spinner = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);
        spinner.setEditor(editor);
        return spinner;
    }

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());

        JSpinner spinner = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();
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
