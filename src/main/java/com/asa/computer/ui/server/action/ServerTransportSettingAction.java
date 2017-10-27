package com.asa.computer.ui.server.action;

import com.asa.base.applet.open.Open;
import com.asa.computer.transfer.Constant;
import com.asa.computer.ui.server.ServerUi;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Created by asa on 2017/8/20.
 * 服务器端传输相关设置
 */
public class ServerTransportSettingAction implements ActionListener {

    private JFrame jFrame;

    private ServerUi serverUi;

    private JTabbedPane jTabbedpane;

    private JPanel pathS;

    private JPanel netS;

    public ServerTransportSettingAction(JFrame jFrame, ServerUi serverUi) {

        this.jFrame = jFrame;
        this.serverUi = serverUi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        beforeShow();
        initTable();
    }

    /**
     * 显示上一次设定之前需要先进行清除
     */
    private void beforeShow() {

        serverUi.clearContain();
    }

    private void initTable() {

        Container container = jFrame.getContentPane();
        container.setLayout(new BorderLayout());
        jTabbedpane = new JTabbedPane();
        container.setLayout(new GridLayout(1, 1));
        container.add(jTabbedpane);

        pathS = new JPanel();
        jTabbedpane.addTab("路径设置", null, pathS, "设置传输路径等");
        initPathSettingPanel();

        netS = new JPanel();
        jTabbedpane.addTab("网络设置", null, netS, "设置服务器默认网络配置等");
        initNetSettingPanel();
    }

    private void initPathSettingPanel() {

        Box sbox = Box.createHorizontalBox();
        final JTextField rvePathT = new JTextField(Constant.TRANSPORTBASEPATH, 20);
        rvePathT.setMaximumSize(rvePathT.getPreferredSize());
        final JLabel revPath = new JLabel("文件传输路径");
        revPath.setMaximumSize(revPath.getPreferredSize());
        JButton revPathChange = new JButton("更改路径");
        JButton revPathOpen = new JButton("打开文件夹");
        revPathChange.setMaximumSize(revPathChange.getPreferredSize());
        revPathOpen.setMaximumSize(revPathOpen.getPreferredSize());
        revPathChange.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int clickCount = e.getClickCount();
                if (1 == clickCount) {
                    JFileChooser jfc = new JFileChooser("选择文件传输路径");
                    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    jfc.showDialog(new JLabel(), "确定");
                    File file = jfc.getSelectedFile();
                    if (file != null) {
                        Constant.TRANSPORTBASEPATH = file.getAbsolutePath();
                        rvePathT.setText(Constant.TRANSPORTBASEPATH);
                    }
                }
            }
        });
        revPathOpen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int clickCount = e.getClickCount();
                if (1 == clickCount) {
                    Open.openInBrowser(Constant.TRANSPORTBASEPATH);
                }
            }
        });
        sbox.add(revPath);
        sbox.add(rvePathT);
        sbox.add(revPathChange);
        sbox.add(revPathOpen);
        Box vb = Box.createVerticalBox();
        sbox.setMaximumSize(sbox.getPreferredSize());
        vb.add(sbox);
        pathS.add(vb);
    }

    private void initNetSettingPanel() {

    }
}
