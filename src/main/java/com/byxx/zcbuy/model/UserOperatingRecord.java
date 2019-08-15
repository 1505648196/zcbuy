package com.byxx.zcbuy.model;

import lombok.Data;

import java.util.Date;

/**
 * user_operating_record
 * @author 
 */
@Data
public class UserOperatingRecord {
    /**
     * 操作记录id
     */
    private String id;

    /**
     * 操作人ID
     */
    private String userId;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作耗时（ms）
     */
    private String elapsedTime;

    /**
     * 请求对象
     */
    private String requestMethod;

}