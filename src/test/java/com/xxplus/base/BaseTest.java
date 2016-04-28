package com.xxplus.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lifang on 2015/1/22.
 */
public class BaseTest{

    public Logger logger = LoggerFactory.getLogger(BaseTest.class);

    private Long startTime = null;

    @After
    public void after(){
        Long endTime = System.currentTimeMillis();
        logger.info("=========================================================");
        logger.info(":::::::::::::::::::::::::::run time = {}ms", (endTime - startTime));
        logger.info("=========================================================");
    }

    @Before
    public void before(){
        startTime = System.currentTimeMillis();
    }

    @Test
    public void testRun(){

    }


}
