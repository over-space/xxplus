package com.xxplus.activiti.service.test.chapter02;

import com.xxplus.activiti.core.WorkflowBaseService;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016-03-05.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MultiInstanceParallelTest extends WorkflowBaseService {


    @Test
    public void testDeploy() {
        deploy("MultiInstanceParallel.bpmn", "多实例之并行执行");
    }

    @Test
    public void startProcessInstance() {
        String processInstanceKey = "MultiInstanceParallel";
        Map<String, Object> variables = new HashMap<>();
        variables.put("emp", "李四");
        final List<String> emps = Arrays.asList("emp1", "emp2", "emp3", "emp4", "emp5");
        variables.put("emp", emps);
        startProcessInstanceByKey(processInstanceKey, "BUS-02", variables);
    }

    @Test
    public void compile() {
        Task task = getTaskByAssingeId("BUS-02", "李四");

        Map<String, Object> variables = new HashMap<>();

        final List<String> emps = Arrays.asList("emp1", "emp2", "emp3", "emp4", "emp5");
        variables.put("loop", emps.size());
        variables.put("emps", emps);
        complete(task.getId(), variables);

        for(String emp : emps){
            Assert.assertEquals(1, taskService.createTaskQuery().taskAssignee(emp).count());
        }
    }

    @Test
    public void compileSub() {
        Task task = getTaskByAssingeId("BUS-02", "e1");
        complete(task.getId());
        long count = historyService.createHistoricActivityInstanceQuery().activityInstanceId("110001").finished().count();



        logger.info("count : {}", count);
    }


}
