package com.byxx.zcbuy.controller;

import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.model.AreaMerchant;
import com.byxx.zcbuy.model.Goods;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import com.byxx.zcbuy.utils.ResultBean;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/addCommodity")
    public String addCommodity(){
        return "admin/merchandiseControl/addCommodity";
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/businessArea")
    public String businessArea(){
        return "admin/businessArea/businessArea";
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/editCommodity")
    public String editCommodity(){
        return "admin/merchandiseControl/editCommodity";
    }


    /**
     * 跳转
     * @return
     */
    @RequestMapping("/editBusinessArea")
    public String editBusinessArea(){
        return "admin/businessArea/editBusinessArea";
    }




    /**
     *商品更新
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateGoods")
    public Object updateGoods(Goods goods) {
        return RestTemplateUtil.post(MyUrl.UPDATEGOODS,goods,LoginInterceptor.getId());
    }

    /**
     *添加商品
     * @return
     */
    @ResponseBody
    @RequestMapping("/addGoods")
    public Object addGoods(Goods goods) {
        return RestTemplateUtil.post(MyUrl.ADD_GOODS,goods,LoginInterceptor.getId());
    }

    /**
     *供应商地区更新
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateAreaMerchant")
    public Object updateAreaMerchant(AreaMerchant areaMerchant) {
        return RestTemplateUtil.post(MyUrl.UPDATE_AREAMER_CHANT,areaMerchant,LoginInterceptor.getId());
    }
    /**
     * 删除供应商地区对象
     */
    @ResponseBody
    @GetMapping("/delAreaMerchant")
    public Object delAreaMerchant(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.DEL_AREA_MERCHANT+request.getAttribute("p"), LoginInterceptor.getId());
    }





}
