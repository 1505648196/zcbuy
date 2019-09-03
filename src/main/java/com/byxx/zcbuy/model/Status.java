package com.byxx.zcbuy.model;

import lombok.Data;
import java.io.Serializable;

/**
 * 状态表
 */


@Data
public class Status implements Serializable {
    /**
     * 状态表ID
     */

    private Integer id;

    /**
     * 状态名
     */
    private String name;




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