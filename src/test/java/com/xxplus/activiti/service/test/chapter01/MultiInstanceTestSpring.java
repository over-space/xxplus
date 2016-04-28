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
 * Created by admin on 2016-02-16.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MultiInstanceTestSpring extends WorkflowSpringBaseService {

    @Test
    public void deploy(){
        InputStream inputStream = this.getClass().getResourceAsStream("/activiti/jbpm/Multi_Instance.bpmn");
        Deployment deployment = repositoryService.createDeployment()
                .name("多实例流程任务")
                .addInputStream("Multi_Instance.bpmn", inputStream)
                .deploy();

        logger.info("部署ID:{}", deployment.getId());
        logger.info("部署名称：{}", deployment.getName());
    }

    @Test
    public void startProcessInstance(){
        String processInstanceKey = "MultiInstance";
        Map<String, Object> variables = new HashMap<>();
        variables.put("organizer", "organizer");

        ProcessInstance processInstance = startProcessInstanceByKey(processInstanceKey, "BusinessKey-1001", variables);

        logger.info("流程定义ID:{}", processInstance.getId());
        logger.info("流程定义KEY：{}", processInstance.getProcessDefinitionKey());
        logger.info("流程定义名称：{}", processInstance.getName());
    }

    @Test
    public void getTaskByAssingeId(){
        List<Task> tasks = getCurrentTask("BusinessKey-1001");

        Map<String, Object> variables = new HashMap<>();
        variables.put("empId", "汪汪");

        for(Task task : tasks){
            complete(task.getId(), variables);
            logger.info("当前完成任务ID:{}", task.getId());
            logger.info("当前完成任务NAME:{}", task.getName());
        }

        tasks = getCurrentTask("BusinessKey-1001");
        for(Task task : tasks){
            logger.info("下一个任务ID:{}", task.getId());
            logger.info("下一个任务NAME:{}", task.getName());
        }

    }
}
