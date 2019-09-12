package com.byxx.zcbuy.model;


import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;

/**
 * 商品小类 分类
 */

@Data
public class GoodsType implements Serializable {
    /**
     * 商品小类Id
     */

    private String id;

    /**
     * 商品小类名称
     */
    private String name;

    /**
     * 采购类型Id
     */
    private String purchaseTypeId;



    private PurchaseType purchaseType;



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