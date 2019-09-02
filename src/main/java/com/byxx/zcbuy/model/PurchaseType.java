package com.byxx.zcbuy.model;


import lombok.Data;

import java.io.Serializable;

/**
 * 采购类型表
 */

@Data
public class PurchaseType implements Serializable {
    /**
     * 采购类型ID
     */

    private String id;

    /**
     * 采购类型名称
     */
    private String name;

    /**
     * 流程定义关联的key
     */
    private String processDefinitionKey;



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