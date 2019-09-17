package com.byxx.zcbuy.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.model.Area;
import com.byxx.zcbuy.model.Role;
import com.byxx.zcbuy.model.RolePlus;
import com.byxx.zcbuy.model.UserPower;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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
    public Object updateUserPower(UserPower userPower) {
        return RestTemplateUtil.post(MyUrl.UPDATE_USER_POWER,userPower, LoginInterceptor.getId());
    }

    /**
     * 用户权限更新
     * updateRolePower
     */
    @ResponseBody
    @PostMapping("/updateRolePowerPlus")
    public Object updateRolePower( @RequestBody Role role) {
        System.out.println(JSONObject.toJSONString(role));
        return RestTemplateUtil.post(MyUrl.UPDATE_ROLE_POWER,role, LoginInterceptor.getId());
}

/*    @ResponseBody
    @PostMapping("/updateRolePowerPlus")
    public Object updateRolePower() {
        String url="http://chunyin1992.vicp.io/api/power/updateRolePower";
        String id="b037f22e9b174a06ba0c13152285fac9";
        HashMap<Object, Object> m = new HashMap<>();
        m.put("id","3");
        m.put("objs",new int[]{1,2,3});
        m.put("name","业务负责人3");
        System.err.println(JSONObject.toJSONString(m));
        return RestTemplateUtil.post(url,JSONObject.toJSONString(m),id);
    }*/

    @ResponseBody
    @PostMapping("/t")
    public Object t() {
        String url="http://chunyin1992.vicp.io/api/power/updateRolePower";
        String id="b037f22e9b174a06ba0c13152285fac9";
        HashMap<Object, Object> m = new HashMap<>();
        m.put("id","3");
        m.put("objs",new int[]{1,2,3});
        m.put("name","业务负责人3");
        System.err.println(JSONObject.toJSONString(m));
        Object post = RestTemplateUtil.post(url,JSONObject.toJSONString(m),id);
        String s = JSONObject.toJSONString(post);
        System.err.println(s);
        System.err.println("我进来了！");
        return post;
    }
}




