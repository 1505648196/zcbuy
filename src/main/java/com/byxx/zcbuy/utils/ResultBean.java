package com.byxx.zcbuy.utils;

import lombok.Data;

@Data
public class ResultBean {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final boolean True = true;
    public static final boolean False = false;

    private String status = ERROR;

    private boolean result = False;

    private String msg;

    private String requestToken;

    private Object[] objs;

    private Object data;

}