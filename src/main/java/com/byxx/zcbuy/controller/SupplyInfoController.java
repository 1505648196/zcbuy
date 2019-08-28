package com.byxx.zcbuy.controller;


import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/supplyUnitRelation")
    public String supplyUnitRelation(){
        return "admin/supplyUnitRelation";
    }

    /**
     * 筛选获取供货商与单位关联信息
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/getSupplyBy")
    public Object getUserBy(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.UNIT_MERCHANT+request.getAttribute("p"), LoginInterceptor.getId());
    }



}
