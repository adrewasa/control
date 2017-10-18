package com.asa.computer.conf;

import java.io.File;

/**
 * @author andrew.asa
 * @create 2017-10-15
 **/
public abstract class ClientConfigure extends BaseConfigure {

    static final String ROOTDIR = "client";

    public ClientConfigure() {

    }

    abstract String getXmlTag();

    public String getChildPath() {

        return ROOTDIR + File.separator + getXmlTag();
    }
}
