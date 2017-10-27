package com.asa.computer.ui.promise;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andrew_asa on 2017/8/14.
 */
public interface MenuBarActionLifeCycle extends ActionListener {

    void beforeMenuBarAction(ActionEvent e,MenuBarActionLifeCycle last);

    /**
     * 切换菜单
     *
     * @param event
     */
    void changeMenuBarAction(ActionEvent event);
}
