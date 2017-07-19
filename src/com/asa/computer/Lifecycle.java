package com.asa.computer;

/**
 * Created by andrew_asa on 2017/7/18.
 */
public interface Lifecycle {

    void init();

    void beforeStart();

    void start();

    void beforeFinish();

    void finish();

}
