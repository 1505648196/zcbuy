package com.byxx.zcbuy.model;

import lombok.Data;

import java.util.Date;

/**
 * goods
 * @author 
 */
@Data
public class Goods {
    /**
     * 商品id
     */
    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格（分，RMB）
     */
    private Long price;

    /**
     * 供货商用户id（商家）
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商品状态
     */
    private Integer status;
}