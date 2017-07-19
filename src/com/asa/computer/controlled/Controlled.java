package com.asa.computer.controlled;

import com.asa.computer.AbstractControl;
import com.asa.utils.computer.control.picture.GuiCamera;
import com.asa.utils.net.IPUtils;

import java.awt.*;
import java.awt.event.InputEvent;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by andrew_asa on 2017/7/18.
 */
public class Controlled extends AbstractControl {


    public void start() {

        System.out.println(IPUtils.getLocalIP());
        System.out.println(IPUtils.getMacByIp(""));
        System.out.println(IPUtils.getMacByIp("192.168.0.12"));


        try {

            Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();

            while (nifs.hasMoreElements()) {
                NetworkInterface nif = nifs.nextElement();

                // 获得与该网络接口绑定的 IP 地址，一般只有一个
                Enumeration<InetAddress> addresses = nif.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet4Address) { // 只关心 IPv4 地址
                        System.out.println("网卡接口名称：" + nif.getName());
                        System.out.println("网卡接口地址：" + addr.getHostAddress());
                        System.out.println();
                    }
                }
            }
            // 获得本机的所有网络接口
        } catch (Exception e) {

        }
    }
}
