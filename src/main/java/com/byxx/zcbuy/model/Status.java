package com.byxx.zcbuy.model;

import lombok.Data;

import java.beans.Transient;
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


    /**
     * 若有新状态在其后添加，禁止修改其顺序
     */

    public final static String[] NAMES = new String[]{
            "正常","冻结","注销", "已提交", "订单处理中", "已完成", "驳回", "挂起", "取消", "激活", "可用", "不可用", "二次驳回-系统取消", "上架", "已下架",
    };



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