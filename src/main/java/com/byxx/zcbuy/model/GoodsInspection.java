package com.byxx.zcbuy.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品签收单
 *
 * @author
 */

@Data
public class GoodsInspection implements Serializable {
    /**
     * 商品验收清单id
     */
    private String id;

    /**
     * 供应商ID
     */
    private String merchantId;

    /**
     * 采购申请单ID
     */
    private String purchaseRequisitionListId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 验收单图片地址
     */
    private String imgUrl;

    /**
     * 签收人姓名
     */
    private String inspectionName;

    /**
     * 备注
     */
    private String comment;

    /**
     * 签收日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inspectionTime;

    /**
     * 验收单编码
     */
    private String inspectionCode;


    private User merchant;

    private PurchaseRequisitionList purchaseRequisitionList;

    private String beginCreateTime;
    private String endCreateTime;

    private String beginInspectionTime;

    private String endInspectionTime;

    private String taskId;


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