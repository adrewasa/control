package com.asa.log;

import com.asa.utils.log.LoggerUtils;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * Created by andrew_asa on 2017/8/17.
 * 日记测试
 */
public class LogTest {


    @Test
    public void testLog() {

        Logger LOGGER = LoggerUtils.getLogger(this.getClass());
        LOGGER.info("hhhhh");
    }
}
