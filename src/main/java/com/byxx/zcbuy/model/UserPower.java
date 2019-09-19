package com.byxx.zcbuy.model;

import com.byxx.zcbuy.model.Power;
import com.byxx.zcbuy.model.User;
import lombok.Data;


import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Data
public class UserPower implements Serializable {

    /**
     * 用户权限ID
     */
    private Integer id;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 权限id
     */
    private Integer powerId;


    private User user;

    private Power power;

    /*子权限集合*/
    private List<Power> powers;

    private String children_ids;

    private String fatherPowers_ids;
    /*更新用户权限是用的，跟新的权限id集合*/

    private Set<Integer> objs;

    private Object obj;

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
