package com.byxx.zcbuy.controller;



import com.alibaba.fastjson.JSON;
import com.byxx.zcbuy.model.*;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.NpResult;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author lze
 * 权限
 */
@Controller
public class PowerrController {

    @RequestMapping("/editPermission")
    public  String editPermission(){
        return "admin/editPermission";
    }

    @RequestMapping("/userRole")
    public String userRole(){
        return "userRole";
    }

    /**
     * 菜单控制
     */
    @ResponseBody
    @GetMapping("/nav")
    public Object nav(HttpServletRequest request){
        return RestTemplateUtil.get(MyUrl.NAV+request.getAttribute("p"), LoginInterceptor.getId());
    }

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

    /**
     * getPowers获取权限表全部权限
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/getPowers")
    public Object getPowers(HttpServletRequest request){
        return RestTemplateUtil.get(MyUrl.GET_POWERS+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     * 獲取全部角色對應權限
     * getPowerByRoles
     */
    @ResponseBody
    @GetMapping("/getPowerByRoles")
    public Object getPowerByRoles(HttpServletRequest request){
        return RestTemplateUtil.get(MyUrl.GET_POWER_BY_ROLES+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     * 删除角色
     * delRole
     */
    @ResponseBody
    @GetMapping("/delRole")
    public Object delRole(HttpServletRequest request){
        return RestTemplateUtil.get(MyUrl.DEL_ROLE+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     *增加新角色
     * addRole
     */
    @ResponseBody
    @PostMapping("/addRole")
    public Object addRole(Role role) {
        return RestTemplateUtil.post(MyUrl.ADD_ROLE,role, LoginInterceptor.getId());
    }
    /**
     * 查询全部商家
     * getMerchants
     */
    @ResponseBody
    @GetMapping("/getMerchants")
    public Object getMerchants(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_MERCHANTS+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     * 更新地区
     *	updateArea
     */
    @ResponseBody
    @PostMapping("/updateArea")
    public Object updateArea(Area area) {
        return RestTemplateUtil.post(MyUrl.UPDATE_AREA,area, LoginInterceptor.getId());
    }


    /**
     * 多条件筛选地区 获取全部地区
     * getAreas
     */
    @ResponseBody
    @GetMapping("/getAreas")
    public Object getAreas(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_AREAS+request.getAttribute("p"), LoginInterceptor.getId());
    }


    /**
     * 增加地区
     * addArea
     */
    @ResponseBody
    @PostMapping("/addArea")
    public Object addArea(Area area) {
        return RestTemplateUtil.post(MyUrl.ADD_AREA,area, LoginInterceptor.getId());
    }

    /**
     * 删除地区
     * delArea
     */
    @ResponseBody
    @GetMapping("/delArea")
    public Object delArea(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.DEL_AERA+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     * 根据用户id获取该用户全部权限
     * getUserPowers
     */
    @ResponseBody
    @GetMapping("/getUserPowers")
    public Object getUserPowers(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_USER_POWERS+request.getAttribute("p"), LoginInterceptor.getId());
    }

    /**
     * 用户权限更新
     * updateUserPower
     */
    @ResponseBody
    @PostMapping("/updateUserPower")
    public Object updateUserPower( @RequestParam Map<String,String> map) {
        UserPower u = new UserPower();//别把对象放进参数,否则会报错
        u.setUserId(map.get("id"));
        map.remove("id");
        map.remove("name");
        System.out.println();
        if (map.size() > 0) {
            Set<Integer> set=new HashSet<Integer>();
            Collection<String> values = map.values();
            for (String value : values) {
                Integer zz = Integer.valueOf(value);
                set.add(zz);
            }
            u.setObjs(set);
        }
        return RestTemplateUtil.post(MyUrl.UPDATE_USER_POWER,u, LoginInterceptor.getId());
    }

    /**
     * 用户权限更新
     * updateRolePower
     */
    @ResponseBody
    @PostMapping("/updateRolePowerPlus")
    public Object updateRolePower( @RequestParam Map<String,String> map,Role role) {
//        直接接受表单
        String id = map.get("id");
        Integer s = null;
        if(id!=null){
            s = Integer.valueOf(id);
        }
        String name = map.get("name");
        if (StringUtils.isBlank(id) || StringUtils.isBlank(name)) {
            return NpResult.error("参数有误!");
        }
        map.remove("id");
        map.remove("name");
        Role r = new Role();
        role.setId(s);
        role.setName(name);
        if (map.size() > 0) {
            Set<Integer> set=new HashSet<Integer>();
            Collection<String> values = map.values();
            for (String value : values) {
                Integer zz = Integer.valueOf(value);
                set.add(zz);
                zz = null;
            }
          role.setObjs(set);
        }
        return RestTemplateUtil.post(MyUrl.UPDATE_ROLE_POWER,role, LoginInterceptor.getId());
}


    /**
     * 获取权限表分级权限
     * getAllPowers
     */

    @ResponseBody
    @GetMapping("/getAllPowers")
    public Object getAllPowers(HttpServletRequest request) {
        return RestTemplateUtil.get(MyUrl.GET_ALL_POWERS+request.getAttribute("p"), LoginInterceptor.getId());
    }




}




