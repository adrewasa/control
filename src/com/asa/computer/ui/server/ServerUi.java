package com.asa.computer.ui.server;

import com.asa.computer.transfer.server.Server;
import com.asa.computer.ui.server.action.ServerTransportSettingAction;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andrew_asa on 2017/8/14.
 * 服务器端
 */
public class ServerUi {

    private Container container;

    private JFrame jFrame;

    private Server server;

    private boolean haveStartServer = false;

    public ServerUi() {

        jFrame = new JFrame("服务器端");
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

        JMenu setting = new JMenu("设置");
        JMenuItem transport = new JMenuItem("传输");
        JMenuItem exit = new JMenuItem("退出");
        menuBar.add(setting);
        setting.add(transport);
        setting.addSeparator();
        setting.add(exit);
        transport.addActionListener(new ServerTransportSettingAction(jFrame,this));
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] options = {"确定", "取消"};
                int response = JOptionPane.showOptionDialog(jFrame, "是否真的退出服务器?", "退出", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (response == 0) {
                    if (beforeExit()) {
                        System.exit(0);
                    }
                } else if (response == 1) {
                    // 取消
                }

            }
        });

        JMenu serverM = new JMenu("网络");
        JMenuItem listClient = new JMenuItem("客户端");
        JMenuItem startServer = new JMenuItem("启动服务器");
        JMenuItem stopServer = new JMenuItem("停止服务器");
        serverM.add(listClient);
        serverM.add(startServer);
        serverM.add(stopServer);

        // 启动服务器
        startServer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!haveStartServer) {
                    haveStartServer = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            server = new Server();
                            server.start();
                        }
                    }).start();
                }
            }
        });
        // 停止服务器
        stopServer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                stopServer();
            }
        });
        menuBar.add(serverM);
    }

    private void stopServer() {

        if (server != null) {
            server.stop();
            server = null;
            haveStartServer = false;
        }
    }

    /**
     * @return 是否关闭
     */
    public boolean beforeExit() {

        // 停止服务器
        stopServer();
        return true;
    }

    public void clearContain() {

        if (jFrame != null) {
            jFrame.getContentPane().removeAll();
            jFrame.repaint();
        }
    }

    public static void main(String[] args) {

        ServerUi serverUi = new ServerUi();
    }


}
