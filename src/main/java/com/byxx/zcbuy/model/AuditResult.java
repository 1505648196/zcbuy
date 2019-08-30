package com.byxx.zcbuy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data

public class AuditResult implements Serializable {
    /**
     *
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    private Integer page;

    private  Integer limit;

    public Integer getPage() {
        if(page==null||page<1){
            page=1;
        }
        return page;
    }

    public Integer getLimit() {
        if(limit==null||limit<1){
            limit=10;
        }
        return limit;
    }
}