package com.xxplus.activiti.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/12/5.
 */
public class FlowTaskVO implements Serializable{

    public final static String TASK_START = "start";
    public final static String TASK_END = "end";

    /**
     * 流程编号
     */
    private String id;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 流程处于的位置
     */
    private int sort = 1;


    private boolean isFlowBack = false;

    /**
     * 任务处理人，原则上一个任务只能有一个受理人，如果有多个，任意一个执行任务
     */
    private List<AssigneeVO> assignees = new ArrayList<>();

    public FlowTaskVO(){}

    public FlowTaskVO(String id, String name, int sort) {
        this.id = id;
        this.name = name;
        this.sort = sort;
    }

    public FlowTaskVO(String id, String name, int sort, AssigneeVO assigneeVO) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.assignees.add(assigneeVO);
    }

    public FlowTaskVO(String id, String name, int sort, boolean isFlowBack, AssigneeVO assigneeVO) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.isFlowBack = isFlowBack;
        this.assignees.add(assigneeVO);
    }

    public static List<FlowTaskVO> getTestFlowTaskList(){
        List<FlowTaskVO> flowTasks = new ArrayList<>();
        FlowTaskVO flowTask1 = new FlowTaskVO("task1", "笔试", 1, new AssigneeVO("US10001", "Jane"));
        FlowTaskVO flowTask2 = new FlowTaskVO("task2", "人事面试", 2, true, new AssigneeVO("US10001", "Jane"));
        FlowTaskVO flowTask3 = new FlowTaskVO("task3", "部门面试", 3, true, new AssigneeVO("US10002", "Tom"));
        flowTasks.add(flowTask1);
        flowTasks.add(flowTask2);
        flowTasks.add(flowTask3);
        return flowTasks;
    }


    public static List<FlowTaskVO> getTestFlowTaskList2(){
        List<FlowTaskVO> flowTasks = new ArrayList<>();
        FlowTaskVO flowTask1 = new FlowTaskVO("task1", "人事审批", 1, new AssigneeVO("US10001", "Jane"));
        FlowTaskVO flowTask2 = new FlowTaskVO("task2", "部门领导审批", 2, true, new AssigneeVO("US10001", "Jane"));
        FlowTaskVO flowTask3 = new FlowTaskVO("task3", "人事核对", 3, true, new AssigneeVO("US10001", "Jane"));
        flowTasks.add(flowTask1);
        flowTasks.add(flowTask2);
        flowTasks.add(flowTask3);
        return flowTasks;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isFlowBack(){
        return isFlowBack;
    }

    public void setFlowBack(boolean isFlowBack){
        this.isFlowBack = isFlowBack;
    }

    public List<AssigneeVO> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<AssigneeVO> assignees) {
        this.assignees = assignees;
    }
}
