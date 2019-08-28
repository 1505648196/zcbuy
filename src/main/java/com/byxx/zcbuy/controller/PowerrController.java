package com.byxx.zcbuy.controller;


import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PowerrController {
    /**
     * 查询所有的权限
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/getPermissionBy")
    public Object getPermissionBy(HttpServletRequest request){
        return RestTemplateUtil.get(MyUrl.GET_USER_BY+request.getAttribute("p"), LoginInterceptor.getId());
    }

    @RequestMapping("/editPermission")
    public  String editPermission(){
        return "admin/editPermission";
    }

    @RequestMapping("/userRole")
    public String userRole(){
        return "admin/UserRole";
    }

}




