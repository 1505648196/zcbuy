package com.byxx.zcbuy.model;

import lombok.Data;

/**
 * power
 * @author 
 */
@Data
public class Power {
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

}