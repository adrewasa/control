package com.asa.computer.ui.promise.imp;

import com.asa.computer.ui.promise.MenuBarActionLifeCycle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andrew_asa on 2017/8/16.
 */
public class MenuBarAction implements ActionListener {

    MenuBarActionLifeCycle action;

    public MenuBarAction(MenuBarActionLifeCycle action) {
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
