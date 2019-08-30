package com.byxx.zcbuy.controller;

import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import com.byxx.zcbuy.utils.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("/editCommodity")
    public String addCommodity(){
        return "admin/merchandiseControl/editCommodity";
    }

    /**
     *商品更新
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateGoods")
    public Object updateGoods(ResultBean resultBean) {
        return RestTemplateUtil.post(MyUrl.UPDATEGOODS,resultBean,LoginInterceptor.getId());
    }



}
