package com.asa.computer.ui.client.action;

import com.asa.computer.transfer.Constant;
import com.asa.computer.ui.client.ClientUi;
import com.asa.utils.applet.open.Open;

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
 * Created by andrew_asa on 2017/8/19.
 * 传输相关设置
 */
public class TransportSettingAction implements ActionListener {

    private JFrame jFrame;

    private ClientUi clientUi;

    private JTabbedPane jTabbedpane;

    private JPanel pathS;

    private JPanel netS;

    public TransportSettingAction(JFrame jFrame, ClientUi clientUi) {

        this.jFrame = jFrame;
        this.clientUi = clientUi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        beforeAction();
        initTable();
    }

    private void beforeAction() {

        clientUi.clearContainer();
    }

    private void initTable() {

        Container container = jFrame.getContentPane();
        container.setLayout(new BorderLayout());
        jTabbedpane = new JTabbedPane();
        container.setLayout(new GridLayout(1, 1));
        container.add(jTabbedpane);

        pathS = new JPanel();
        jTabbedpane.addTab("路径设置", null, pathS, "设置文件存放路径等");
        initPathSettingPanel();

        netS = new JPanel();
        jTabbedpane.addTab("网络设置", null, netS, "设置默认网络配置等");
        initNetSettingPanel();
    }

    private void initPathSettingPanel() {

        //BoxLayout boxLayout = new BoxLayout(pathS,BoxLayout.X_AXIS);
        //pathS.setLayout(boxLayout);
        //pathS.setPreferredSize(new Dimension(10,400));
        Box sbox = Box.createHorizontalBox();
        final JTextField rvePathT = new JTextField(Constant.TRANSPORTREVEIVEPATH, 20);
        rvePathT.setMaximumSize(rvePathT.getPreferredSize());
        final JLabel revPath = new JLabel("文件下载路径");
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
                    JFileChooser jfc = new JFileChooser("选择文件下载路径");
                    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    jfc.showDialog(new JLabel(), "确定");
                    File file = jfc.getSelectedFile();
                    if (file != null) {
                        Constant.TRANSPORTREVEIVEPATH = file.getAbsolutePath();
                        rvePathT.setText(Constant.TRANSPORTREVEIVEPATH);
                    }
                }
            }
        });
        revPathOpen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int clickCount = e.getClickCount();
                if (1 == clickCount) {
                    Open.openInBrowser(Constant.TRANSPORTREVEIVEPATH);
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
