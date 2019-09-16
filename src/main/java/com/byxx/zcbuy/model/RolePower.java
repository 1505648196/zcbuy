package com.byxx.zcbuy.model;

import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;


@Data
public class RolePower implements Serializable {

    /**
     * 角色权限id
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer powerId;




    private Role role;

    private List<Power> powers;

    /*拥有的全部一级权限*/
    private String fatherPowers_ids;

    /*拥有的全部二级权限*/
    private String children_ids;

    public RolePower(){}

    public RolePower(Integer id ,Integer roleId,Integer powerId){
        this.id = id;
        this.roleId = roleId;
        this.powerId = powerId;
    }


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
