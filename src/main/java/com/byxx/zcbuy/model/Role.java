package com.byxx.zcbuy.model;

import lombok.Data;

import java.io.Serializable;

import java.util.List;
import java.util.Set;


@Data
public class Role implements Serializable {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    private List<Integer> objs;

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
