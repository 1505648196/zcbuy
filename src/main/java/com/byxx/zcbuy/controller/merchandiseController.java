package com.byxx.zcbuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class merchandiseController {
    /**
     * 跳转
     * @return
     */
    @RequestMapping("/commodity")
    public String productInfo(){

        return "admin/merchandiseControl/commodity";
    }

}
