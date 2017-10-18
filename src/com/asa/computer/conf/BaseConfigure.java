package com.asa.computer.conf;

import java.io.File;

/**
 * @author andrew.asa
 * @create 2017-10-15
 * 基础配置类
 **/
public abstract class BaseConfigure {

    static final String ROOTDIR = "conf";

    abstract String getChildPath();

    public String getStorePath() {

        return ROOTDIR + File.separator + getChildPath();
    }
}
