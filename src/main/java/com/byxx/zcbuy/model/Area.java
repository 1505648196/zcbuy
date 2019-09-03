package com.byxx.zcbuy.model;

import lombok.Data;
import java.io.Serializable;


@Data
public class Area implements Serializable {
    /**
     * 供货地区id
     */

    private Integer id;

    /**
     * 地区名
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