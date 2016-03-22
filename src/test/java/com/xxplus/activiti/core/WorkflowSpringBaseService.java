package com.xxplus.activiti.core;

import com.alibaba.fastjson.JSON;
import com.xxplus.activiti.vo.AssigneeVO;
import com.xxplus.activiti.vo.FlowTaskVO;
import com.xxplus.activiti.listener.ExecutionEndListener;
import com.xxplus.activiti.listener.ExecutionStartListener;
import com.xxplus.activiti.listener.ExecutionTakeListener;
import com.xxplus.base.SpringBaseTest;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015-12-26.
 */
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class WorkflowSpringBaseService extends SpringBaseTest {

    @Autowired public RepositoryService repositoryService;
    @Autowired public FormService formService;
    @Autowired public IdentityService identityService;
    @Autowired public TaskService taskService;
    @Autowired public RuntimeService runtimeService;
    @Autowired public HistoryService historyService;


    public void deploy(String fileName, String name){

        String path = "/activiti/diagrams/" + fileName;

        InputStream inputStream = this.getClass().getResourceAsStream(path);
        Deployment deployment = repositoryService.createDeployment()
                .name(name)
                .addInputStream(fileName, inputStream)
                .deploy();

        logger.info("部署ID:{}", deployment.getId());
        logger.info("部署名称：{}", deployment.getName());
    }

    /**
     * 部署流程
     * @param flowTasks
     */
    public void deploy(String processId, String processName, List<FlowTaskVO> flowTasks, boolean isFormProcess){

        Process process = new Process();
        process.setId(processId);

        FlowNode lastLastFlowNode = null; //上上个节点
        FlowNode lastFlowNode = isFormProcess ? createFormStartEvent() : createStartEvent();//上一个节点, 默认为开始节点
        FlowNode currentFlowNode; //当前节点

        boolean isBack = false;
        boolean isFormProp = true;
        process.addFlowElement(lastFlowNode);
        for(FlowTaskVO task : flowTasks){
            currentFlowNode = createUserTask(task, isFormProp);
            addFlowElement(process, lastLastFlowNode, lastFlowNode, currentFlowNode, isBack);

            isFormProp = false;
            isBack = task.isFlowBack();
            lastLastFlowNode = lastFlowNode;
            lastFlowNode = currentFlowNode;
        }

        //添加结束事件
        addFlowElement(process, lastLastFlowNode, lastFlowNode, createEndEvent(), false);

        //2. 添加监听事件
        List<ActivitiListener> listeners = new ArrayList<>();
        listeners.add(createEventListener(ImplementationType.IMPLEMENTATION_TYPE_CLASS, ExecutionListener.EVENTNAME_START, ExecutionStartListener.class));
        listeners.add(createEventListener(ImplementationType.IMPLEMENTATION_TYPE_CLASS, ExecutionListener.EVENTNAME_END, ExecutionEndListener.class));
        listeners.add(createEventListener(ImplementationType.IMPLEMENTATION_TYPE_CLASS, ExecutionListener.EVENTNAME_TAKE, ExecutionTakeListener.class));
        process.setExecutionListeners(listeners);

        BpmnModel model = new BpmnModel();
        model.addProcess(process);
        new BpmnAutoLayout(model).execute();
        repositoryService.createDeployment().addBpmnModel(processId + ".bpmn", model).name(processName).deploy();
    }


    /**
     * 启动流程
     *
     * @param processDefineKey 流程KEY
     * @param businessKey 业务ID
     * @return 流程实例
     */
    public ProcessInstance startProcessInstanceByKey(String processDefineKey, String businessKey){
        return runtimeService.startProcessInstanceByKey(processDefineKey, businessKey);
    }


    /**
     * 启动流程
     *
     * @param processDefineKey 流程KEY
     * @param businessKey 业务ID
     * @return 流程实例
     */
    public ProcessInstance startProcessInstanceByKey(String processDefineKey, String businessKey, Map<String, Object> variables){
        return runtimeService.startProcessInstanceByKey(processDefineKey, businessKey, variables);
    }

    /**
     * 提交表单
     * @param processDefineId
     * @param variables
     * @return
     */
    public ProcessInstance submitStartFormData(String processDefineId, Map<String, String> variables){
        return formService.submitStartFormData(processDefineId, variables);
    }

    /**
     * 查询当前全部任务
     *
     * @param processInstanceBusinessKey
     * @return
     */
    public List<org.activiti.engine.task.Task> getCurrentTask(String processInstanceBusinessKey){
        return taskService.createTaskQuery().processInstanceBusinessKey(processInstanceBusinessKey).list();
    }


    /**
     * 查询用户任务
     *
     * @param processInstanceBusinessKey
     * @param assingeId
     * @return
     */
    public org.activiti.engine.task.Task getTaskByAssingeId(String processInstanceBusinessKey, String assingeId){
        return taskService.createTaskQuery().processInstanceBusinessKey(processInstanceBusinessKey).taskCandidateOrAssigned(assingeId).singleResult();
    }

    public List<org.activiti.engine.task.Task> getAllTaskByAssingeId(String processInstanceBusinessKey, String assingeId){
        return taskService.createTaskQuery().processInstanceBusinessKey(processInstanceBusinessKey).taskCandidateOrAssigned(assingeId).list();
    }


    /**
     * 获取表单变量
     * @param processInstanceId
     * @return
     */
    public List<HistoricDetail> getHistoryFormVariables(String processInstanceId){
        return historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).list();
    }


    /**
     * 处理任务
     * @param taskId
     */
    public void complete(String taskId){
        taskService.complete(taskId);
    }

    /**
     * 处理任务
     * @param taskId
     */
    public void complete(String taskId, Map<String, Object> variables){
        taskService.complete(taskId, variables);
    }

    /**
     * @param process 流程对象
     * @param lastLastFlowNode 上上一步流程
     * @param lastFolwNode 上一步流程
     * @param currentFlowNode 当前流程
     * @param isAllowBack 是否允许退回
     */
    private void addFlowElement(Process process, FlowNode lastLastFlowNode, FlowNode lastFolwNode, FlowNode currentFlowNode, boolean isAllowBack) {
        process.addFlowElement(currentFlowNode);
        if(lastLastFlowNode == null || lastFolwNode instanceof StartEvent || !isAllowBack){

            SequenceFlow sequenceFlow = new SequenceFlow();//表示一个顺序流
            sequenceFlow.setSourceRef(lastFolwNode.getId());//顺序流的源
            sequenceFlow.setTargetRef(currentFlowNode.getId());//顺序流目的地
            process.addFlowElement(sequenceFlow);
        }else{

            ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
            exclusiveGateway.setId("XOR_" + lastFolwNode.getId());
            exclusiveGateway.setName("XOR_" + lastFolwNode.getId());
            process.addFlowElement(exclusiveGateway);

            //上一步连接网关
            SequenceFlow sequenceFlow = new SequenceFlow();
            sequenceFlow.setSourceRef(lastFolwNode.getId());
            sequenceFlow.setTargetRef(exclusiveGateway.getId());
            process.addFlowElement(sequenceFlow);

            //下一步
            SequenceFlow nextFlow = new SequenceFlow();
            nextFlow.setSourceRef(exclusiveGateway.getId());
            nextFlow.setTargetRef(currentFlowNode.getId());
            nextFlow.setConditionExpression("${isPass == 'true'}");
            process.addFlowElement(nextFlow);

            //退回
            SequenceFlow preFlow = new SequenceFlow();
            preFlow.setSourceRef(exclusiveGateway.getId());
            preFlow.setTargetRef(lastLastFlowNode.getId());
            preFlow.setConditionExpression("${isPass == 'false'}");
            process.addFlowElement(preFlow);
        }
    }


    /**
     * 创建开始节点事件
     *
     * @return
     */
    private StartEvent createStartEvent(){
        StartEvent startEvent = new StartEvent();
        startEvent.setId(FlowTaskVO.TASK_START);
        startEvent.setName(FlowTaskVO.TASK_START);
        return startEvent;
    }

    private StartEvent createFormStartEvent(){
        StartEvent startEvent = new StartEvent();
        startEvent.setId(FlowTaskVO.TASK_START);
        startEvent.setName(FlowTaskVO.TASK_START);

        List<FormProperty> formProperties = new ArrayList<>();
        formProperties.add(getFormProperty("startDate", "请假开始日期", "string", true, true));
        formProperties.add(getFormProperty("endDate", "请假结束日期", "string", true, true));
        formProperties.add(getFormProperty("reason", "请假原因", "string", true, true));
        startEvent.setFormProperties(formProperties);
        return startEvent;
    }

    private FormProperty getFormProperty(String id, String name, String type, boolean required, boolean readable){
        FormProperty formProperty = new FormProperty();
        formProperty.setId(id);
        formProperty.setName(name);
        formProperty.setType(type);
        formProperty.setRequired(required);
        formProperty.setReadable(readable);

//        formProperty.setVariable("${" + id + "}");
        switch (type){
            case "date":
                formProperty.setDatePattern("yyyy-MM-dd");
                break;
            case "string":
                break;
            case "enum":
                List<FormValue> formValues = new ArrayList<>();
                formValues.add(getFormValue("true", "同意"));
                formValues.add(getFormValue("false", "拒绝"));
                formProperty.setFormValues(formValues);
                break;
        }

        return formProperty;
    }

    private FormValue getFormValue(String id, String name){
        FormValue formValue = new FormValue();
        formValue.setId(id);
        formValue.setName(name);
        return formValue;
    }

    /**
     * 创建结束节点事件
     *
     * @return
     */
    private EndEvent createEndEvent(){
        EndEvent endEvent = new EndEvent();
        endEvent.setId(FlowTaskVO.TASK_END);
        endEvent.setName(FlowTaskVO.TASK_END);
        return endEvent;
    }


    /**
     * 创建任务，并设置任务受理人或受理角色
     *
     * @param task
     * @return UserTask
     */
    private UserTask createUserTask(FlowTaskVO task, boolean isFormProp){
        UserTask userTask = new UserTask();
        userTask.setId(getTaskId(task.getSort()));
        userTask.setName(task.getName());
        userTask.setDocumentation(JSON.toJSONString(task));

        List<String> empIds = new ArrayList<>();
        List<String> roleIds = new ArrayList<>();

        List<AssigneeVO> assignees = task.getAssignees();
        for(AssigneeVO assignee : assignees){
            switch (assignee.getAssigneeType()){
                case EMP:
                    empIds.add(assignee.getId());
                    break;
                case ROLE:
                    roleIds.add(assignee.getId());
                    break;
                default:
                    break;
            }
        }

        if(!empIds.isEmpty()) {
            userTask.setCandidateUsers(empIds);
        }else if(!roleIds.isEmpty()) {
            userTask.setCandidateGroups(roleIds);
        }

        if(isFormProp) {
            List<FormProperty> formProperties = new ArrayList<>();
            formProperties.add(getFormProperty("startDate", "请假开始日期", "string", true, true));
            formProperties.add(getFormProperty("endDate", "请假结束日期", "string", true, true));
            formProperties.add(getFormProperty("reason", "请假原因", "string", true, true));
            formProperties.add(getFormProperty("deptApprove", "审批意见", "enum", true, false));
            userTask.setFormProperties(formProperties);
        }

        return userTask;
    }


    private ActivitiListener createEventListener(String implementationType, String executionListener, Class listenerClass){
        ActivitiListener activitiListener = new ActivitiListener();
        activitiListener.setImplementationType(implementationType);
        activitiListener.setEvent(executionListener);
        activitiListener.setImplementation(listenerClass.getName());
        return activitiListener;
    }

    /**
     * 获取任务ID,产生规则："TASK-" + 流程序号
     *
     * @param flowSort
     * @return 任务ID
     */
    private String getTaskId(int flowSort){
        return "TASK-" + flowSort;
    }
}
