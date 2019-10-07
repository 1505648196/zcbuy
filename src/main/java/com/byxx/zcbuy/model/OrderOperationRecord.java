package com.byxx.zcbuy.model;


import lombok.Data;


import java.beans.Transient;
import java.io.Serializable;


/**
 * 订单操作记录表
 */

@Data
public class OrderOperationRecord implements Serializable {
    /**
     * 订单操作记录id
     */

    private String id;

    /**
     * 订单id
     */
    private String purchaseRequisitionListId;

    /**
     * 操作时间
     */
    private String createTime;

    /**
     * 操作用户id
     */
    private String userId;

    /**
     * 操作内容
     */
    private String content;



    private User user;

    private PurchaseRequisitionList purchaseRequisitionList;


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