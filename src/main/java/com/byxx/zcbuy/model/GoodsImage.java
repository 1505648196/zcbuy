package com.byxx.zcbuy.model;


import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;

/**
 * 商品图片表
 */

@Data
public class GoodsImage implements Serializable {
    /**
     * 商品图id
     */

    private String id;

    /**
     * 商品Id
     */
    private String goodsId;

    /**
     * 商品图地址
     */
    private String imageUrl;



    private Integer page;

    private Integer limit;

    public Integer getPage ( ) {
        if (page == null || page < 1) {
            page = 1;
        }
        return page;
    }

    public Integer getLimit ( ) {
        if (limit == null || limit < 1) {
            limit = 10;
        }
        return limit;
    }
}