package com.asa.computer.ui.client.action;

import com.asa.utils.CommonUtil;
import com.asa.utils.applet.btscan.BTScan;
import com.asa.utils.applet.btscan.NBResponseNode;
import com.asa.utils.log.LoggerUtils;
import com.asa.utils.net.IPUtils;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by asa on 2017/8/19.
 * 查找局域网中的服务器
 */
public class FindServer implements ActionListener {

    private JFrame jFrame;

    private boolean isScanning = false;

    public FindServer(JFrame jFrame) {

        this.jFrame = jFrame;
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
        Container container = jFrame.getContentPane();
        container.removeAll();
        jFrame.validate();
        jFrame.repaint();
        return false;
    }
}
