package com.byxx.zcbuy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 采购申请单
 */

@Data
public class PurchaseRequisition implements Serializable {
    /**
     * 采购申请表ID
     */

    protected String id;

    /**
     * 申请人ID
     */
    private String userId;

    /**
     * 采购商品
     */
    private String goodsName;

    /**
     * 采购数量
     */
    private Integer quantity;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String comment;

    /**
     * 采购类型ID
     */
    private String purchaseTypeId;

    /**
     * 单位送货价格
     */
    private Long price;


    /**
     * 流程审批人Id
     */

    private String assigneeId;

    /**
     * 供应商Id
     */

    private String merchantId;

    /**
     * 采购类型
     */

    private String purchaseTypeName;

    /**
     * 申请人
     */

    private User user;

    /**
     * 用于标记任务接受者为一个单位的情况（劳保采购）
     */

    private Integer unitId;




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