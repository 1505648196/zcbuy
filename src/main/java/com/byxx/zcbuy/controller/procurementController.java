package com.byxx.zcbuy.controller;


import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.model.PurchaseRequisition;
import com.byxx.zcbuy.model.Unit;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 采购
 */
@Controller
public class procurementController {

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/buyApply")
    public String productInfo(){
        return "admin/procurement/buyApply";
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/buyType")
    public String buyType(){
        return "admin/procurement/buyType";
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/addBuyApply")
    public String addBuyApply(){
        return "admin/procurement/addBuyApply";
    }


    /**
     *新增采购申请
     * @return
     */
    @ResponseBody
    @PostMapping("/addPurchaseRequisition")
    public Object addPurchaseRequisition(PurchaseRequisition PurchaseRequisition) {
        return RestTemplateUtil.post(MyUrl.ADD_PURCHASE_REQUISITION,PurchaseRequisition, LoginInterceptor.getId());
    }






}
