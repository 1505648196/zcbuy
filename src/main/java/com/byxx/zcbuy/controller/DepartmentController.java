package com.byxx.zcbuy.controller;


import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lze
 *部门
 */
@Controller
public class DepartmentController {


    /**
     * 跳转
     * @return
     */
    @RequestMapping("/department")
    public String productInfo(){
        return "admin/department/department";
    }


    /**
     * 查询全部部门
     * getDepartments
     */
    @ResponseBody
    @RequestMapping("/getDepartments")
    public Object getDepartments(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_DEPARTMENTS+request.getAttribute("p"), LoginInterceptor.getId());
    }




}
