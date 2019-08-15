package com.byxx.zcbuy.model;

import lombok.Data;

/**
 * rolepower
 * @author 
 */
@Data
public class Rolepower {
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

}