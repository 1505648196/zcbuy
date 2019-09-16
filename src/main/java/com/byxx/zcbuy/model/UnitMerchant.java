package com.byxx.zcbuy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
public class UnitMerchant implements Serializable {
    /**
     * 供货商单位关联表Id
     */

    private String id;

    /**
     * 供货商ID (跟user表关联)
     */
    private String userId;

    /**
     * 单位ID
     */
    private Integer unitId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 供应商名称
     */
    private String merchantName;



    private User user;

    private Unit unit;

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