package com.byxx.zcbuy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作流中用到的任务表
 */

@Data
public class Task implements Serializable {


    private String id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date startTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 任务执行人
     */
    private String assignee;

    /**
     * 流程定义的key
     */
    private String processDefinitionKey;

    /**
     * 任务拾取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date claimTime;

    /**
     * 相关业务ID
     */
    private String businessKey;

    /**
     * 任务详情
     */
    private String detail;

    /**
     * 工作流中task表的ID
     */
    private String taskId;







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