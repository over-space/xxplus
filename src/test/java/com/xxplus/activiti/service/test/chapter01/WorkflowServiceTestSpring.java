package com.xxplus.activiti.service.test.chapter01;

import com.xxplus.activiti.vo.FlowTaskVO;
import com.xxplus.activiti.core.WorkFlowParams;
import com.xxplus.activiti.core.WorkflowSpringBaseService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015-12-26.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class WorkflowServiceTestSpring extends WorkflowSpringBaseService {

    private static final String PROCESS_ID = "AC201601";
    private static final String PROCESS_NAME = "请假流程";
    private static final String BUSINESS_KEY = "BS201501";

    @Before
    public void testDeploy(){
        deploy(PROCESS_ID, PROCESS_NAME, FlowTaskVO.getTestFlowTaskList2(), true);
    }

    @Test
    public void testStartProcess2(){
        //查询流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(PROCESS_ID).singleResult();
        logger.info("process definition id : {}", processDefinition.getId());
        logger.info("process definition key : {}", processDefinition.getKey());
        logger.info("process definition name : {}", processDefinition.getName());


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String startDate = sdf.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 4);
        String endDate = sdf.format(calendar.getTime());

        Map<String, String> variables = new HashMap<>();
        variables.put("startDate", startDate);
        variables.put("endDate", endDate);
        variables.put("reason", "世界这么大,我想去看看.");
//        ProcessInstance processInstance = startProcessInstanceByKey(processDefinition.getKey(), BUSINESS_KEY, variables);
        ProcessInstance processInstance = submitStartFormData(processDefinition.getId(), variables);

        logger.info("process instance id : {}", processInstance.getId());
        logger.info("process instance deployment id : {}", processInstance.getDeploymentId());
        logger.info("process instance name : {}", processInstance.getName());
        logger.info("process definition version : {}", processInstance.getProcessDefinitionVersion());

        //获取表单变量
        List<HistoricDetail> historicDetails = getHistoryFormVariables(processInstance.getId());
        for(HistoricDetail historicDetail : historicDetails){
            HistoricFormProperty historicFormProperty = (HistoricFormProperty) historicDetail;
            logger.info("property id : {}", historicFormProperty.getPropertyId());
            logger.info("property value : {}", historicFormProperty.getPropertyValue());
        }
    }


    @Test
    public void testStartProcess(){
        //查询流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(PROCESS_ID).singleResult();
        logger.info("process definition id : {}", processDefinition.getId());
        logger.info("process definition key : {}", processDefinition.getKey());
        logger.info("process definition name : {}", processDefinition.getName());

        //启动流程
        ProcessInstance processInstance = startProcessInstanceByKey(processDefinition.getKey(), BUSINESS_KEY);
        logger.info("process instance id : {}", processInstance.getId());
        logger.info("process instance deployment id : {}", processInstance.getDeploymentId());
        logger.info("process instance name : {}", processInstance.getName());
        logger.info("process definition version : {}", processInstance.getProcessDefinitionVersion());

        //查询当前全部任务
        getCurrentAllTask();

        //查询用户任务
        Task task = getTaskByAssingeId(BUSINESS_KEY, "US10001");
        Assert.assertNotNull(task);
        complete(task.getId());
        getCurrentAllTask();

        Map<String, Object> variables = new HashMap<>();
        variables.put(WorkFlowParams.IS_PASS, true);

        task = getTaskByAssingeId(BUSINESS_KEY, "US10001");
        Assert.assertNotNull(task);
        complete(task.getId(), variables);
        getCurrentAllTask();

        task = getTaskByAssingeId(BUSINESS_KEY, "US10002");
        Assert.assertNotNull(task);
        complete(task.getId(), variables);

        //查询当前全部任务
        getCurrentAllTask();
    }

    private void getCurrentAllTask(){
        List<Task> taskList = getCurrentTask(BUSINESS_KEY);
        for(Task task : taskList){
            logger.info("task id : {}", task.getId());
            logger.info("task name : {}", task.getName());
        }
    }
}
