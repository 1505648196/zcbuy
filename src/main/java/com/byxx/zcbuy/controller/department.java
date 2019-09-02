package com.byxx.zcbuy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class department {


    /**
     * 跳转
     * @return
     */
    @RequestMapping("/department")
    public String productInfo(){
        return "admin/department/department";
    }




}
