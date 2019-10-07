package com.byxx.zcbuy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.beans.Transient;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 历史操作记录表
 */

public class UserOperatingRecord implements Serializable {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 请求对象
     */
    private String method;


    private Integer page;

    private  Integer limit;

    /*操作人姓名*/
    private  String name;

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

    public UserOperatingRecord(){}
    public UserOperatingRecord(String id,String ip_address,String create_time,String method,String user_id) throws ParseException {
        this.id = id;
        this.ipAddress = ip_address;
        this.method = method;
        this.userId = user_id;
        this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(create_time);
    }
}
