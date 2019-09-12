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
 * @lze
 * 流程图
 */
@Controller
public class processController {
    @RequestMapping("processImg")
    public String processImg(){
        return "admin/processImg/processImg";
    }

    /**
     * 显示流程图
     *getProcessPng
     */
    @ResponseBody
    @GetMapping("/getProcessPng")
    public Object getProcessPng(HttpServletRequest request){
        return RestTemplateUtil.get(MyUrl.GET_PROCESSPNG+request.getAttribute("p"), LoginInterceptor.getId());
    }


}
