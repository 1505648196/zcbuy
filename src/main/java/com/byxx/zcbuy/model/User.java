package com.byxx.zcbuy.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Data
public class User implements Serializable {
    /**
     * 用户id
     */

    private String id;

    /**
     * 登陆用户名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 所属单位
     */
    private Integer unitId;

    /**
     * 所属部门
     */
    private Integer departmentId;

    /**
     * 用户角色
     */
    private Integer roleId;

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
     * 是否锁定账户（0.未锁定   1.已锁定）
     */
    private Integer isLock;

    /**
     * 账户状态（0.未通过审核  1.已通过审核）
     */
    private Integer status;



    private Unit unit;

    private Department department;

    private Role role;

    private Set objs;

    private Object obj;

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
