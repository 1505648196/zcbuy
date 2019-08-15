package com.byxx.zcbuy.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String id;
    private String loginName;
    private String loginPwd;
    private String name;
    private String phone;
    private String email;
    private Integer unitId;
    private Integer departmentId;
    private Integer roleId;
    private String createTime;
    private String updateTime;
    private Integer isLock;
    private Integer status;

    private Unit unit;
    private Department department;
    private Role role;
    private List objs;
    private Object obj;
    private Integer pageNo;
}
