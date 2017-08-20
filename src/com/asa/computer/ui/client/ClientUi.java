package com.asa.computer.ui.client;

import com.asa.computer.transfer.client.Client;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.ui.client.action.FileListAction;
import com.asa.computer.ui.client.action.FindServer;
import com.asa.computer.ui.client.action.ShutdownServerAction;
import com.asa.computer.ui.client.action.ThemeSettingAction;
import com.asa.computer.ui.client.action.TransportSettingAction;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andrew_asa on 2017/7/30.
 */
public class ClientUi {

    private Container container;

    private JFrame jFrame;

    JTextArea contentTa = null;


    public ClientUi() {

        jFrame = new JFrame("客户端");
        container = jFrame.getContentPane();
        setLayout();
        setDefault();
        setMenu();
        //setContent();
        jFrame.setVisible(true);
    }

    public void setLayout() {

    }

    public void setDefault() {

        jFrame.setSize(600, 400);
        jFrame.setLocation(400, 300);
        // 设置关闭退出
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
    }

    public void setMenu() {

        JMenuBar menuBar = new JMenuBar();
        jFrame.setJMenuBar(menuBar);

        JMenu fileM = new JMenu("文件");
        JMenuItem fileLI = new JMenuItem("文件列表");
        fileLI.addActionListener(new FileListAction(jFrame,this));
        JMenuItem item2 = new JMenuItem("保存");
        JMenuItem item3 = new JMenuItem("另存为");
        JMenuItem exit = new JMenuItem("退出");
        menuBar.add(fileM);
        fileM.add(fileLI);
        fileM.add(item2);
        fileM.add(item3);
        fileM.addSeparator();
        fileM.add(exit);
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] options = {"确定", "取消"};
                int response = JOptionPane.showOptionDialog(jFrame, "是否真的退出?", "退出", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (response == 0) {
                    if (beforeExit()) {
                        System.exit(0);
                    }
                } else if (response == 1) {
                    // 取消
                }
            }
        });

        JMenu serverM = new JMenu("服务器");
        JMenuItem serverInfoI = new JMenuItem("服务器信息");
        JMenuItem stopServerI = new JMenuItem("停止服务器");
        JMenuItem shutdownServerI = new JMenuItem("服务器关机");
        stopServerI.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Client client = new Client();
                client.actionCmd(RequestConstant.CMD_STOPSERVER);
            }
        });
        shutdownServerI.addActionListener(new ShutdownServerAction(this));
        serverM.add(serverInfoI);
        serverM.add(stopServerI);
        serverM.add(shutdownServerI);
        menuBar.add(serverM);

        JMenu networkM = new JMenu("网络");
        JMenuItem findServerI = new JMenuItem("查找服务器");
        JMenuItem connectServerI = new JMenuItem("断开连接");
        JMenuItem scanNet = new JMenuItem("扫描局域网");
        networkM.add(findServerI);
        networkM.add(connectServerI);
        networkM.add(scanNet);
        menuBar.add(networkM);
        findServerI.addActionListener(new FindServer(jFrame, this));

        JMenu settingM = new JMenu("设置");
        menuBar.add(settingM);
        JMenuItem transportSetting = new JMenuItem("传输设置");
        JMenuItem themeSetting = new JMenuItem("主题设置");
        settingM.add(transportSetting);
        settingM.add(themeSetting);
        transportSetting.addActionListener(new TransportSettingAction(jFrame, this));
        themeSetting.addActionListener(new ThemeSettingAction(jFrame));

        JMenu helpM = new JMenu("帮助");
        menuBar.add(helpM);
        JMenuItem updateI = new JMenuItem("更新服务器");
        helpM.add(updateI);
        updateI.addActionListener(new UpdateServer());

    }

    public void setContent() {

        container.setLayout(new BorderLayout());
        contentTa = new JTextArea(10, 15);
        contentTa = new JTextArea(10, 15);
        contentTa.setTabSize(4);
        contentTa.setLineWrap(true);// 激活自动换行功能
        JScrollPane jscrollPane = new JScrollPane(contentTa);
        container.add(jscrollPane, BorderLayout.CENTER);
    }

    /**
     * 退出之前的操作
     *
     * @return 是否退出
     */
    public boolean beforeExit() {

        return true;
    }

    /**
     * 更新软件
     */
    private class UpdateServer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public void clearContainer() {

        if (jFrame != null) {
            jFrame.getContentPane().removeAll();
            jFrame.repaint();
        }
    }

    public void addNewContainer(Component component) {

        jFrame.getContentPane().add(component);
    }

    public JFrame getjFrame() {

        return jFrame;
    }

    public static void main(String[] args) {

        ClientUi ui = new ClientUi();
        //JFrame jf = new JFrame("dd");
        //jf.setSize(400, 300);
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //jf.setVisible(true);
        //JPanel contentPane = new JPanel();
        //jf.setContentPane(contentPane);
        //Ls ls = new Ls();
        //LsNode lsNode = ls.getSimpleLsNode();
        //for (LsNode n : lsNode.getChild()) {
        //    JLabel label = new JLabel();
        //    label.setText(n.getName());
        //    contentPane.add(label);
        //}
        //JLabel label1=new JLabel();
        //JLabel label2=new JLabel();
        //contentPane.add(label1);
        //contentPane.add(label2);
        //label1.setText("11");
        //label2.setText("11");
    }
}
