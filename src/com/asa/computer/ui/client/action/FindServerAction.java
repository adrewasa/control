package com.asa.computer.ui.client.action;

import com.asa.computer.ui.client.ClientUi;
import com.asa.utils.CommonUtil;
import com.asa.utils.applet.btscan.BTScan;
import com.asa.utils.applet.btscan.NBResponseNode;
import com.asa.utils.applet.btscan.NetBiosResponse;
import com.asa.utils.log.LoggerUtils;
import com.asa.utils.net.IPUtils;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by asa on 2017/8/19.
 * 查找局域网中的服务器
 */
public class FindServerAction implements ActionListener {

    private JFrame jFrame;

    private ClientUi clientUi;

    private JPanel jPanel;

    private JScrollPane jScrollPane ;

    private boolean isScanning = false;

    public FindServerAction(JFrame jFrame, ClientUi clientUi) {

        this.jFrame = jFrame;
        this.clientUi = clientUi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            // 返回true代表正在扫描
            if (beforeScan()) {
                return;
            }
            // 扫描当前网段
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    LoggerUtils.getLogger(this.getClass()).info("start to scan Local network server");
                    BTScan btScan = new BTScan(new String[0]);
                    List<String> ips = IPUtils.getLocalIpSegment(1, 255);
                    List<NBResponseNode> ns = btScan.getNodes(ips, true);
                    afterScan(ns);
                }
            });
            t.start();
        } catch (Exception e1) {
            LoggerUtils.getLogger(this.getClass()).info("error in scan local network server", e1);
        }
    }

    /**
     * 扫描完成之后的回调
     *
     * @param ns
     */
    private void afterScan(List<NBResponseNode> ns) {

        isScanning = false;
        LoggerUtils.getLogger(this.getClass()).info("end scan Local network server,{} server have found", ns.size());
        if (CommonUtil.isEmptyList(ns)) {
            return;
        }
        jPanel = new JPanel();
        //jFrame.getContentPane().add(jPanel);
        JLabel label = new JLabel();
        label.setText("总共发现" + ns.size() + "台电脑，选择连接的电脑");
        jPanel.add(label);
        Box vbox = Box.createVerticalBox();
        JLabel info = getIntroduceLabel();
        info.setMaximumSize(info.getPreferredSize());
        vbox.add(info);
        for (NBResponseNode n : ns) {
            JLabel l = getLabel(n);
            TitledBorder tb = BorderFactory.createTitledBorder("");
            l.setBorder(tb);
            l.setMaximumSize(l.getPreferredSize());
            vbox.add(l);
            //jPanel.add(l);
        }
        jScrollPane = new JScrollPane(vbox);
        //jPanel.setLayout(new ChangeLineFlowLayout(ChangeLineFlowLayout.LEFT));
        jScrollPane.setBounds(100, 100, 100, 300);
        jFrame.getContentPane().add(jScrollPane);
        jFrame.validate();
        jFrame.repaint();
    }

    private JLabel getLabel(final NBResponseNode node) {

        final NetBiosResponse biosResponse = node.getResponse();
        JLabel label = new JLabel(biosResponse.getFormatStr());
        final FindServerAction self = this;
        label.addMouseListener(new MouseAdapter() {

            // 右击弹出框
            public void mousePressed(MouseEvent e) {

                int witch = e.getButton();
                if (witch == MouseEvent.BUTTON1) {
                    //System.out.println("left");
                } else if (witch == MouseEvent.BUTTON2) {
                    //System.out.println("middle");
                } else if (witch == MouseEvent.BUTTON3) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem download = new JMenuItem("连接");
                    popupMenu.add(download);
                    download.addActionListener(new ConnectServerAction(biosResponse, self));
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        return label;
    }


    private JLabel getIntroduceLabel() {

        JLabel label = new JLabel(BTScan.getIntroduceStr());
        return label;
    }

    /**
     * 扫描之前的操作，需要清空内容面板，禁止事件响应等
     */
    private boolean beforeScan() {
        // 如果正在扫描
        if (isScanning) {
            return true;
        }
        isScanning = true;
        clientUi.clearContainer();
        return false;
    }

    public JPanel getjPanel() {

        return jPanel;
    }

    public ClientUi getClientUi() {

        return clientUi;
    }

    public JFrame getjFrame() {

        return jFrame;
    }
}
