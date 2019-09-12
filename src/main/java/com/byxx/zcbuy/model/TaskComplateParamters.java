package com.byxx.zcbuy.model;


import lombok.Data;

import java.io.Serializable;

/**
 * 提交任务可能用到的参数
 */
@Data
public class TaskComplateParamters implements Serializable {

    /**
     * 任务ID
     */
    String taskId;
    /**
     * 指定的用户集合（任务负责人、任务候选人）
     */
    String str_users;
    /**
     * 是否设置任务责任人
     */
    boolean boo_assignee;
    /**
     * 是否设置任务候选人
     */
    boolean boo_candidate;
    /**
     * 是否取消
     */
    boolean boo_delete;
    /**
     * 是否需要多人审批
     */
    boolean boo_needHelp;
    /**
     * 是否同意通过任务
     */
    boolean boo_pass;

    /**
     * 采购申请单Id（用于驳回后申请单修改，重新发起申请请求）
     */
    String purchaseRequisitionId;


    /**
     * 审批备注
     */
    String activitiComment;

    /**
     * 采购类型Id
     */
    String purchaseTypeId;

    /**
     * 流程定义名称
     */
    String processDefinitionName;

    /**
     * 第几页
     */
    Integer page;

    /**
     * 每页条数
     */
    Integer limit;

    /**
     * 任务完成时间之后
     */
    String taskCompletedAfterTime;

    /**
     * 任务完成时间之前
     */
    String taskCompletedBeforeTime;

    /**
     * 任务创建时间之后
     */
    String taskCreatedAfterTime;

    /**
     * 任务创建时间之前
     */
    String taskCreatedBeforeTime;


    public Integer getPage ( ) {
        if (page == null || page < 1) {
            page = 1;
        }
        return page;
    }

    public Integer getLimit ( ) {
        if (limit == null || limit < 1) {
            limit = 10;
        }
        return limit;
    }
}
