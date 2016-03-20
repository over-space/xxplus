package com.xxplus.activiti.service.test.chapter01;

import com.xxplus.activiti.core.WorkflowBaseService;
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
public class ActivitiParallelGatewayTest extends WorkflowBaseService {


    @Test
    public void deploy() {
        InputStream inputStream = this.getClass().getResourceAsStream("/activiti/jbpm/Gateway_Parallel.bpmn");
        Deployment deployment = repositoryService.createDeployment()
                .name("ParallelGateway")
                .addInputStream("ParallelGateway.bpmn", inputStream)
                .deploy();

        logger.info("部署ID:{}", deployment.getId());
        logger.info("部署名称：{}", deployment.getName());
    }


    @Test
    public void startProcessInstance() {
        String processInstanceKey = "Gateway_Parallel";
        Map<String, Object> variables = new HashMap<>();
        variables.put("hrEmpId", "10001");
        variables.put("deptEmpId", "20001");
        variables.put("seoEmpId", "30001");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processInstanceKey, variables);

        logger.info("流程定义ID:{}", processInstance.getId());
        logger.info("流程定义KEY：{}", processInstance.getProcessDefinitionKey());
        logger.info("流程定义名称：{}", processInstance.getName());
    }

    @Test
    public void findTaskByEmpId() {
        String assignee = "30001";
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();

        for (Task task : tasks) {
            logger.info("Task ID : {}", task.getId());
            logger.info("Task Name : {}", task.getName());

            taskService.complete(task.getId());
        }
    }


}
