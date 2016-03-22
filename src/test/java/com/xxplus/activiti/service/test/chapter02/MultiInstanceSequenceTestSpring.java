package com.xxplus.activiti.service.test.chapter02;

import com.xxplus.activiti.core.WorkflowSpringBaseService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016-03-05.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MultiInstanceSequenceTestSpring extends WorkflowSpringBaseService {


    @Test
    public void testDeploy(){
        deploy("MultiInstanceSequence.bpmn", "多实例之顺序执行");
    }

    @Test
    public void startProcessInstance(){
        String processInstanceKey = "MultiInstanceSequence";
        Map<String, Object> variables = new HashMap<>();
        variables.put("emp", "张三");

        startProcessInstanceByKey(processInstanceKey, "BUS-01", variables);
    }

    @Test
    public void compile(){
        Task task = getTaskByAssingeId("BUS-01", "张三");

//        Map<String, Object> variables = new HashMap<>();
//        variables.put("loop", "5");
//        complete(task.getId(), variables);

        complete(task.getId());

    }
}
