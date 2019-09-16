package com.byxx.zcbuy.model;

import lombok.Data;



import java.beans.Transient;
import java.io.Serializable;


@Data
public class Department implements Serializable {
    /**
     * 部门id
     */

    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门所属单位
     */
    private Integer unitId;


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
