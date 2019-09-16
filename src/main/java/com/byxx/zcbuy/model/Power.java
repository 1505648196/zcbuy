package com.byxx.zcbuy.model;

import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

@Data

public class Power implements Serializable {

    /**
     * 权限id
     */
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限地址
     */
    private String url;

    /**
     * 父节点id
     */
    private Integer pid;


    private List<Power> children;

    private String children_ids;

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
