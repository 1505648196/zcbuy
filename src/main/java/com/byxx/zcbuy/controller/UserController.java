package com.byxx.zcbuy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.model.User;
import com.byxx.zcbuy.utils.MyUrl;
import com.byxx.zcbuy.utils.NpResult;
import com.byxx.zcbuy.utils.RestTemplateUtil;
import com.byxx.zcbuy.utils.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 聂鹏
 * @description:
 * @create 2019/08/12
 */
@Controller
public class UserController {


	@ResponseBody
	@GetMapping("/t")
	public NpResult t() {
		return NpResult.success();
	}
	@RequestMapping("/")
	public String login() {
		return "login";
	}

	/**
	 * 权限管理
	 * @return
	 */
	@RequestMapping("/permission")
	public String permission(){
		return  "admin/permission";
	}

	@RequestMapping("/noLogin")
	public String noLogin(HttpSession session) {
		session.removeAttribute("userMsg");
		return "login";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/userAdd")
	public String userAdd() {
		return "admin/userAdd";
	}

	@RequestMapping("/welcome")
	public String welcome(Model model) {
		return "welcome";
	}

	@RequestMapping("/myInfo")
	public String myInfo() {
		return "myInfo";
	}

	@RequestMapping("/editPwd")
	public String editPwd() {
		return "editPwd";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}

	@RequestMapping("/editUser")
	public String editUser() {
		return "admin/editUser";
	}

	@RequestMapping("/userRoleplus")
	public String userRoleplus() {
		return "admin/userRoleplus";
	}


	/**
	 * 无用
	 * @return
	 */
	@RequestMapping("/editUSerplus")
	public  String editUSerplus(){
		return "admin/editUSerplus";
	}

	/**
	 * 无用
	 * @return
	 */
	@RequestMapping("/conciseUser")
	public  String conciseUser(){
		return  "admin/conciseUser";

	}

	/***
	 * login start
	 * @param user
	 * @param session
	 * @return
	 */
	@ResponseBody
	@PostMapping("/doLogin")
	public Object doLogin(User user, HttpSession session) {
		Object post = RestTemplateUtil.post(MyUrl.DO_LOGIN, user);
		ResultBean resultBean = JSON.parseObject(JSON.toJSONString(post, SerializerFeature.WriteMapNullValue), ResultBean.class);
		session.setAttribute("userMsg",resultBean.getData());
		System.out.println("nihao");
		System.out.println(resultBean.getData());
		return post;
	}

	@ResponseBody
	@RequestMapping("/getUserById")
	public Object getUserById() {
		return RestTemplateUtil.get(MyUrl.GET_ALL_ROLE,LoginInterceptor.getId());
	}

	@ResponseBody
	@GetMapping("/getAllUnit")
	public Object getAllUnit() {
		return RestTemplateUtil.get(MyUrl.GET_ALL_UNIT, LoginInterceptor.getId());
	}
	@ResponseBody
	@GetMapping("/getDepartment")
	public Object getDepartment(HttpServletRequest request) {
		return RestTemplateUtil.get(MyUrl.GET_DEPARTMENTS_BY+request.getAttribute("p"), LoginInterceptor.getId());
	}
	@ResponseBody
	@GetMapping("/getUserBy")
	public Object getUserBy(HttpServletRequest request) {
		return RestTemplateUtil.get(MyUrl.GET_USER_BY+request.getAttribute("p"), LoginInterceptor.getId());
	}

	@ResponseBody
	@GetMapping("/getAllRole")
	public Object getAllRole() {
		return RestTemplateUtil.get(MyUrl.GET_ALL_ROLE, LoginInterceptor.getId());
	}

	@ResponseBody
	@PostMapping("/delUser")
	public Object delUser(User user) {
		return RestTemplateUtil.post(MyUrl.DEL_USER,user, LoginInterceptor.getId());
	}

	@ResponseBody
	@PostMapping("/updateUserInfo")
	public Object updateUserInfo(User user) {
		return RestTemplateUtil.post(MyUrl.UPDATE_USER_INFO, user, LoginInterceptor.getId());
	}
	@ResponseBody
	@PostMapping("/register")
	public Object register(User user) {
		return RestTemplateUtil.post(MyUrl.REGISTER, user, LoginInterceptor.getId());
	}

	@ResponseBody
	@PostMapping("/updateMyInfo")
	public Object updateMyInfo(User user,HttpSession session) {
		Object post = RestTemplateUtil.post(MyUrl.UPDATE_MY_INFO, user, LoginInterceptor.getId());
		ResultBean resultBean = JSON.parseObject(JSON.toJSONString(post, SerializerFeature.WriteMapNullValue), ResultBean.class);
		session.setAttribute("userMsg",resultBean.getData());
		return NpResult.success();
	}

	@ResponseBody
	@PostMapping("/updatePwd")
	public Object updatePwd(User user) {
		return RestTemplateUtil.post(MyUrl.UPDATE_MY_INFO, user, LoginInterceptor.getId());
	}

}
