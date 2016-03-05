package com.xxplus.code.activiti.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by admin on 2015/12/20.
 */
public class ExecutionTakeListener implements Serializable, ExecutionListener{

    private Logger logger = LoggerFactory.getLogger(ExecutionTakeListener.class);

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        logger.info("流程Take事件...");
    }
}
