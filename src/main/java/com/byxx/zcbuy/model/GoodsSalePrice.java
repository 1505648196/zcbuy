package com.byxx.zcbuy.model;


import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;

/**
 * 商品售价表
 */

@Data
public class GoodsSalePrice implements Serializable {
    /**
     * 售价表id
     */

    private String id;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 售价(单位: 分）
     */
    private Long salePrice;

    /**
     * 备注
     */
    private String comment;



    private Goods goods;


    private Long maxPrice;  //价格区间最高价

    private Long minPrice;  //价格区间最低价


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