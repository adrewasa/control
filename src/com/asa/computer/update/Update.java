package com.asa.computer.update;

import java.io.File;

/**
 * Created by andrew_asa on 2017/7/23.
 *
 * 用于更新软件
 */
public class Update {


    public static void main(String[] args) {

        File f = new File("~");
        System.out.println(f.getAbsolutePath());
    }
}
