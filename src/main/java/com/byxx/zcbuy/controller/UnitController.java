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









}
