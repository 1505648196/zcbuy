package com.byxx.zcbuy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.railway.procurementsystem.tool.DateUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 站段订单结算单（用于数据统计 页面展示）
 */
@Data
public class UnitOrderSettlementList {

    /**
     * 单位编号
     */
    private Integer unitId;

    /**
     * 单位
     */
    private Unit unit;

    /**
     * 订单总价 (元)
     */
    private String totalPrice;

    /**
     * 订单查询起始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    /**
     * 订单查询 起始时间
     */
    private String str_startTime;

    public Date getStartTime ( ) {
        if (queryByYear || queryByMonth) {
            if (StringUtils.isNotBlank(str_startTime)) {
                startTime = DateUtil.strToDate(str_startTime);
            } else {
                startTime = null;
            }
        }
        if (queryByDate) {
            if (StringUtils.isNotBlank(str_startTime)) {
                startTime = DateUtil.getDayBeginTime(DateUtil.strToDate(str_startTime));
            } else {
                startTime = null;
            }
        }
        return startTime;
    }

    /**
     * 订单终止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    /**
     * 订单查询 终止时间
     */
    private String str_endTime;

    public Date getEndTime ( ) {
        if (queryByYear || queryByMonth) {
            if (StringUtils.isNotBlank(str_endTime)) {
                endTime = DateUtil.strToDate(str_endTime);
            } else {
                endTime = null;
            }
        }
        if (queryByDate) {
            if (StringUtils.isNotBlank(str_endTime)) {
                endTime = DateUtil.getDayEndTime(DateUtil.strToDate(str_endTime));
            } else {
                endTime = null;
            }
        }
        return endTime;
    }

    /**
     * 采购类型Id
     */
    private String purchaseTypeId;

    /**
     * 采购类型
     */
    private PurchaseType purchaseType;

    /**
     * 采购订单状态值
     */
    private Integer purchaseStatus;

    /**
     * 状态类
     */
    private Status status;

    /**
     * 商品小类id
     */
    private String GoodsTypeId;

    /**
     * 商品小类
     */
    private GoodsType goodsType;


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

    /**
     * 采购订单集合
     */
    private List< PurchaseRequisitionList > list;


    /**
     * 是否按年查询
     */
    private boolean queryByYear = true;
    /**
     * 是否按月查询
     */
    private boolean queryByMonth;
    /**
     * 是否按日查询
     */
    private boolean queryByDate;

    public void setQueryByYear ( boolean queryByYear ) {
        if (queryByYear) {
            this.queryByYear = true;
            this.queryByMonth = false;
            this.queryByDate = false;
        }
    }

    public void setQueryByMonth ( boolean queryByMonth ) {
        if (queryByMonth) {
            this.queryByYear = false;
            this.queryByMonth = true;
            this.queryByDate = false;
        }
    }

    public void setQueryByDate ( boolean queryByDate ) {
        if (queryByDate) {
            this.queryByYear = false;
            this.queryByMonth = false;
            this.queryByDate = true;
        }
    }


    public UnitOrderSettlementList ( ) {}

    public UnitOrderSettlementList ( UnitOrderSettlementList order ) {
        this.unitId = order.unitId;
        this.unit = order.unit;
        this.totalPrice = order.totalPrice;
        this.startTime = order.startTime;
        this.str_startTime = order.str_startTime;
        this.endTime = order.endTime;
        this.str_endTime = order.str_endTime;
        this.purchaseTypeId = order.purchaseTypeId;
        this.purchaseType = order.purchaseType;
        this.purchaseStatus = order.purchaseStatus;
        this.status = order.status;
        this.GoodsTypeId = order.GoodsTypeId;
        this.goodsType = order.goodsType;
        this.page = order.page;
        this.limit = order.limit;
        this.list = order.list;
        this.queryByYear = order.queryByYear;
        this.queryByMonth = order.queryByMonth;
        this.queryByDate = order.queryByDate;
    }

}
