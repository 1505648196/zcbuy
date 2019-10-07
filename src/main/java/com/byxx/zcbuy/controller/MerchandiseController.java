package com.byxx.zcbuy.controller;

import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.model.AreaMerchant;
import com.byxx.zcbuy.model.Goods;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lze
 * 商品 地区
 */
@Controller
public class MerchandiseController {
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
     * 跳转
     * @return
     */
    @RequestMapping("/addBusinessArea")
    public String addBusinessArea(){
        return "admin/businessArea/addBusinessArea";
    }



    /**
     * 跳转
     * @return
     */
    @RequestMapping("/salesPrice")
    public String salesPrice(){
        return "admin/merchandiseControl/salesPrice";
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/smallGoods")
    public String smallGoods(){
        return "admin/merchandiseControl/smallGoods";
    }



    /**
     * 多条件获取商品列表 全部商品
     * getGoods
     */
    @ResponseBody
    @GetMapping("/getGoods")
    public Object getGoods(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_GOODS+request.getAttribute("p"), LoginInterceptor.getId());
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
     * 删除商品
     */
    @ResponseBody
    @RequestMapping("/delGoods")
    public Object delGoods(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.DEL_GOODS+request.getAttribute("p"), LoginInterceptor.getId());
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
    /**
     * 增加供应商地区对象
     */
    @ResponseBody
    @PostMapping("/addAreaMerchant")
    public Object addAreaMerchant(AreaMerchant areaMerchant) {
        return RestTemplateUtil.post(MyUrl.ADD_AREA_MERCHANT,areaMerchant, LoginInterceptor.getId());
    }
    /**
     *     获取全部地区对象
     */
    @ResponseBody
    @GetMapping("/getAreaAll")
    public Object getAreaAll(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_AREA_ALL+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     *     更新地区
     */
    @ResponseBody
    @GetMapping("/updateArea")
    public Object updateArea(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.UPDATE_AREA+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     *  获取单个商品（id）
     */
    @ResponseBody
    @GetMapping("/getById")
    public Object getById(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_BY_ID+request.getAttribute("p"), LoginInterceptor.getId());
    }


    /**
     * 多条件查询商品销售价(分页)
     * getSalePrices
     */
    @ResponseBody
    @GetMapping("/getSalePrices")
    public Object getSalePrices(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_SALE_PRICES+request.getAttribute("p"), LoginInterceptor.getId());
    }











}
