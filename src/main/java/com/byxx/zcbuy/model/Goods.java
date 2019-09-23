package com.byxx.zcbuy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
public class Goods implements Serializable {
    /**
     * 商品id
     */

    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格（分，RMB）
     */
    private Long price;

    /**
     * 供货商用户id（商家）
     */
    private String userId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 商品状态
     */
    private Integer status;

    /**
     * 商品小类 分类Id
     */
    private String goodsTypeId;

    /**
     * 商品计量单位
     */
    private String measurement;

    public final static String[] measurements = {
        "件","箱","袋","罐","瓶" ,"只","双","套","把","桶",
            "打","卷","罗","辆","头","包","千克",
            "公吨","长吨","短吨","盎司","蒲式耳",
            "公升","加仑","码","米","英尺","厘米",
            "平方米","平方码","平方英尺","平方英寸",
            "立方米","立方码","立方英尺","立方英寸"
    };


    /*状态名*/

    private String statusName;

    /*卖家*/

    private User user;

    /*商品小类*/

    private GoodsType goodsType;
    /*采购类型*/

    private PurchaseType purchaseType;

    /*关联的供货单位集合*/
    /*@Transient
    private List<UnitMerchant> unitMerchants;*/


    private Long maxPrice;  //价格区间最高价

    private Long minPrice;  //价格区间最低价


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