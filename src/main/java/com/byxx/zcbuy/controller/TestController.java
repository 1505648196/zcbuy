package com.byxx.zcbuy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lze
 * 测试专用
 */
@Controller
public class TestController {

    @RequestMapping("Test")
    public String testShow(){
        return "admin/processImg/Test";
    }



}