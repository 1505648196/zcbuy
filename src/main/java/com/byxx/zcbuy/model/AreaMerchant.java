package com.byxx.zcbuy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class AreaMerchant implements Serializable {
    /**
     * 供货地区id
     */

    private String id;

    /**
     * 供货地区
     */
    private Integer areaId;

    /**
     * 供货商id
     */
    private String userId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 地区名
     */
    private String areaName;


    private User user;

    private Area area;

    private String statusName;


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