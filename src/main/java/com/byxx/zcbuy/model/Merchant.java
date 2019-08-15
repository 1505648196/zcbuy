package com.byxx.zcbuy.model;

import lombok.Data;

import java.util.Date;

/**
 * merchant
 * @author 
 */
@Data
public class Merchant {
    /**
     * 商家ID
     */
    private String id;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 密码
     */
    private String loginPwd;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 统一社会信用代码
     */
    private String creditCode;

    /**
     * 企业名
     */
    private String merchantName;

    /**
     * 联系人姓名
     */
    private String name;

    /**
     * 企业地址
     */
    private String address;

    /**
     * 证件图片地址
     */
    private String imgUrl;

    /**
     * 账户状态
     */
    private Integer status;

    /**
     * 是否锁定账户（0.未锁定   1.锁定）
     */
    private Integer isLock;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 角色id
     */
    private Integer roleId;

}