package com.byxx.zcbuy.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.model.UnitMerchant;
import com.byxx.zcbuy.model.User;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.NpResult;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import com.byxx.zcbuy.utils.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author luozien
 *供应商页面
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


    /**
     * 删除供货商与单位关联信息
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/delsupplyUnitRelation")
    public Object delsupplyUnitRelation(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.DELSUPPLYUNITRELATION+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/addRelation")
    public String editRelation(){
        return "admin/addRelation";
    }

    /**
     * 添加供货商与单位关联信息
     * @param unitMerchant
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("/addsupplyUnitRelation")
    public Object addsupplyUnitRelation(UnitMerchant unitMerchant, HttpSession session) {
        return RestTemplateUtil.post(MyUrl.ADDSUPPLYUNITRELATION, unitMerchant, LoginInterceptor.getId());
    }









}
