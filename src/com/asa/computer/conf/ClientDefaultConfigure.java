package com.asa.computer.conf;

/**
 * @author andrew.asa
 * @create 2017-10-15
 **/
public class ClientDefaultConfigure extends ClientConfigure {

    static final String xmlTag = "default.xml";

    /**
     * 服务器ip
     */
    String serverIp;

    /**
     * 服务器命令端口
     */
    String serverCmdPort;

    /**
     * 文件保存基路径
     */
    String transportSaveBasePath;

    @Override
    String getXmlTag() {

        return xmlTag;
    }
}
