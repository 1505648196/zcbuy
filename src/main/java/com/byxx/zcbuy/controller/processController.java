package com.byxx.zcbuy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @lze
 * 流程图
 */
@Controller
public class processController {
    @RequestMapping("woju")
    public String woju(){
        return "admin/processImg/woju";
    }
}
