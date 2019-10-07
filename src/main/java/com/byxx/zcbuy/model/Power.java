package com.byxx.zcbuy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Power  implements Serializable {
    /**
     * 权限id
     */
    private Integer id;

    /**
     * 权限名
     */
    private String title;

    /**
     * 权限地址
     */
    private String href;

    /**
     * 父节点id
     */
    private Integer pid;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型（1、菜单  2、按钮）
     */
    private Integer type;

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

    private List children;

    private String children_ids;

    /**
     * 标题字体图标来源
     */

    private String fontFamily = "ok-icon";


    private boolean spread;

    private boolean isCheck;


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
