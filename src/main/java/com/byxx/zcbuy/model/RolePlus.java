package com.byxx.zcbuy.model;

import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Set;


@Data
public class RolePlus implements Serializable {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;


    /**
     * 用于更新权限
     */

    private Set<Integer> objs;



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
