package com.byxx.zcbuy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 采购申请单
 */

@Data
public class PurchaseRequisitionList implements Serializable {
    /**
     * 采购订单Id
     */

    private String id;

    /**
     * 商品Id
     */
    private String goodsId;

    /**
     * 采购申请人Id
     */
    private String userId;

    /**
     * 采购数量
     */
    private Integer quantity;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 采购类型Id
     */
    private String purchaseTypeId;

    /**
     * 最终送货总价
     */
    private Long totalPrice;

    /**
     * 订单备注
     */
    private String comment;

    /**
     * 审批备注
     */
    private String activitiComment;

    /**
     * 收件人姓名
     */
    private String recipientName;

    /**
     * 收件人联系电话
     */
    private String recipientPhone;

    /**
     * 收件单位
     */
    private String recipientUnit;

    /**
     * 收件详细地址
     */
    private String recipientAddress;

    /**
     * 要求到货时间
     */
    private String requestArrivalTime;

    /**
     * 已收货数量
     */
    private Integer receiptQuantity;



    private User user;

    private Goods goods;

    private PurchaseType purchaseType;

    private String statusName;


    /**
     * 用于标记任务接受者为一个单位的情况（劳保采购）
     */

    private Integer unitId;


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

    public Integer getReceiptQuantity ( ) {
        return receiptQuantity == null ? 0 : receiptQuantity;
    }
}