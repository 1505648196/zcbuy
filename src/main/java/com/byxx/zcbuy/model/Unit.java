package com.byxx.zcbuy.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class Unit implements Serializable {

    /**
     * 单位id
     */
    private Integer id;

    /**
     * 单位名称
     */
    private String name;

    /**
     * 单位地址
     */
    private String address;


    private List<Department> departments;


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
