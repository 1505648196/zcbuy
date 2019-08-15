package com.byxx.zcbuy.model;

import lombok.Data;

/**
 * userpower
 * @author 
 */
@Data
public class Userpower{
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

}