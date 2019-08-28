package com.byxx.zcbuy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author luozien
 *
 */
@Controller
public class SupplyInfoController {

    /**
     * 供应商页面
     * @return
     */
    @RequestMapping("/supplyPlace")
    public String supplyPlace(){
        return "admin/supplyPlace";
    }

    @RequestMapping("/editSupplyPlace")
    public String editSupplyPlace(){
        return "admin/editSupplyPlace";
    }



}
