package com.xxplus.activiti.service.test.chapter01;

import com.xxplus.activiti.core.WorkflowSpringBaseService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016-01-30.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class ActivitiEventGatewayTestSpring extends WorkflowSpringBaseService {


    @Test
    public void deploy(){
        InputStream inputStream = this.getClass().getResourceAsStream("/activiti/jbpm/Gateway_Event.bpmn");
        Deployment deployment = repositoryService.createDeployment()
                .name("Gateway_Event")
                .addInputStream("Gateway_Event.bpmn", inputStream)
                .deploy();

        logger.info("部署ID:{}", deployment.getId());
        logger.info("部署名称：{}", deployment.getName());
    }


    @Test
    public void startProcessInstance() {
        String processInstanceKey = "Gateway_Event";
        Map<String, Object> variables = new HashMap<>();
//        variables.put("applicant", "lisi");
//        variables.put("startDate", "2016-02-14");
//        variables.put("endDate", "2016-02-16");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processInstanceKey, variables);

        logger.info("流程定义ID:{}", processInstance.getId());
        logger.info("流程定义KEY：{}", processInstance.getProcessDefinitionKey());
        logger.info("流程定义名称：{}", processInstance.getName());
    }

    @Test
    public void findTaskByEmpId(){
        String assignee = "wangwu";
        List<Task> tasks  = taskService.createTaskQuery().taskAssignee(assignee).list();

        Map<String, Object> variables = new HashMap<>();
        variables.put("isHrAppr", "true");
        variables.put("isDeptApp", "true");
        variables.put("hrEmpId", "zhangsan");
        variables.put("deptEmpId", "wangwu");

        for(Task task : tasks) {
            logger.info("Task ID : {}", task.getId());
            logger.info("Task Name : {}", task.getName());

//            complete(task.getId(), variables);
            complete(task.getId());
        }
    }


}
