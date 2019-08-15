package com.byxx.zcbuy.model;

import lombok.Data;

/**
 * department
 * @author 
 */
@Data
public class Department {
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

}