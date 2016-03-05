package com.xxplus.code.activiti.vo;

import java.io.Serializable;

/**
 * Created by admin on 2015/12/5.
 */
public class AssigneeVO implements Serializable{

    public enum AssigneeType{EMP, ROLE};

    /**
     * 处理人类型,人员 / 角色
     */
    private AssigneeType assigneeType = AssigneeType.EMP;

    /**
     * 处理人ID
     */
    private String id;

    /**
     * 处理人名称
     */
    private String name;

    public AssigneeVO() {
    }

    public AssigneeVO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public AssigneeVO(String id, String name, AssigneeType assigneeType) {
        this.id = id;
        this.name = name;
        this.assigneeType = assigneeType;
    }

    public AssigneeType getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(AssigneeType assigneeType) {
        this.assigneeType = assigneeType;
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
}
