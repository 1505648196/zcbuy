package com.byxx.zcbuy.model;

import lombok.Data;

import java.util.Date;

/**
 * auditresult
 * @author 
 */
@Data
public class Auditresult {
    /**
     * 审批结果id
     */
    private String id;

    /**
     * 审批人ID
     */
    private String auditUserId;

    /**
     * 审批状态
     */
    private Integer status;

    /**
     * 审批对象角色
     */
    private Integer roleId;

    /**
     * 审批对象ID
     */
    private String userId;

    /**
     * 备注
     */
    private String comment;

    /**
     * 审批时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}