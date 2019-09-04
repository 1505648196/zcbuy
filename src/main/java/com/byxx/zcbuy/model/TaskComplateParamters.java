package com.byxx.zcbuy.model;

import com.byxx.zcbuy.model.PurchaseRequisition;
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
    String[] users;
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
     * 采购申请单（用于驳回后申请单修改，重新发起申请请求）
     */
    PurchaseRequisition purchaseRequisition;

}
