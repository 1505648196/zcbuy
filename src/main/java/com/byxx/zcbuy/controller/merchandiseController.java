package com.byxx.zcbuy.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class merchandiseController {
    /**
     * 跳转
     * @return
     */
    @RequestMapping("/productInfo")
    public String productInfo(){
        return "admin/merchandiseControl/productInfo";
    }

}
