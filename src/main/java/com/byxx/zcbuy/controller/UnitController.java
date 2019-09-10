package com.byxx.zcbuy.controller;


import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.model.Goods;
import com.byxx.zcbuy.model.Unit;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lze
 * 单位
 */
@Controller
public class UnitController {

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/unitAll")
    public String index() {
        return "admin/unit/unitAll";
    }

    /**
     * 跳转
     * @return
     */
    @RequestMapping("/addUnit")
    public String addUnit() {
        return "admin/unit/addUnit";
    }
    /**
     * 跳转
     * @return
     */
    @RequestMapping("/editUnit")
    public String editUnit() {
        return "admin/unit/editUnit";
    }

    /**
     *添加新单位
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUnits")
    public Object addUnit(Unit unit) {
        return RestTemplateUtil.post(MyUrl.ADD_UNIT,unit, LoginInterceptor.getId());
    }


    /**
     *删除新单位
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUnit")
    public Object deleteUnit(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.DELETE_UNIT+request.getAttribute("p"), LoginInterceptor.getId());
    }


    /**
     *单位更新
     * @return
     */
    @ResponseBody
    @PostMapping("/updateUnit")
    public Object updateUnit(Unit unit) {
        return RestTemplateUtil.post(MyUrl.UPDATE_UNIT,unit, LoginInterceptor.getId());
    }

    /**
     * 获取全部单位(分页)
     * getUnitsWithPage
     */
    @ResponseBody
    @RequestMapping("/getUnitsWithPage")
    public Object getUnitsWithPage(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_UNITS_WITH_PAGE+request.getAttribute("p"), LoginInterceptor.getId());
    }


    /**
     * 查询所有和本单位合作的供应商名单
     * selectMerchants
     */
    @ResponseBody
    @RequestMapping("/selectMerchants")
    public Object selectMerchants(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.SELECT_MERCHANTS+request.getAttribute("p"), LoginInterceptor.getId());
    }








}
