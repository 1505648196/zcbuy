package com.byxx.zcbuy.controller;

import com.alibaba.fastjson.JSON;
import com.byxx.zcbuy.config.LoginInterceptor;
import com.byxx.zcbuy.model.User;
import com.byxx.zcbuy.utils.MyUrl;
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


	@RequestMapping("/")
	public String login() {
		return "login";
	}

	@RequestMapping("/noLogin")
	public String noLogin(HttpSession session) {
		session.removeAttribute("userMsg");
		return "login";
	}

	@ResponseBody
	@PostMapping("/doLogin")
	public Object doLogin(User user, HttpSession session) {
		System.err.println(JSON.toJSONString(user));
		Object post = RestTemplateUtil.post(MyUrl.DO_LOGIN, user);
		System.err.println(JSON.toJSONString(post));
		ResultBean resultBean = JSON.parseObject(JSON.toJSONString(post), ResultBean.class);
		session.setAttribute("userMsg",resultBean.getData());
		return post;
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome(Model model) {
		return "welcome";
	}

	@RequestMapping("/myInfo")
	public String myInfo() {
		return "myInfo";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}

	@RequestMapping("/editUser")
	public String editUser() {
		return "admin/editUser";
	}

	@ResponseBody
	@GetMapping("/getAllUnit")
	public Object getAllUnit() {
		return RestTemplateUtil.get(MyUrl.GET_ALL_UNIT, LoginInterceptor.getUser().getId());
	}
	@ResponseBody
	@GetMapping("/getDepartment")
	public Object getDepartment(HttpServletRequest request) {
		return RestTemplateUtil.get(MyUrl.GET_DEPARTMENTS_BY+request.getQueryString(), LoginInterceptor.getUser().getId());
	}
	@ResponseBody
	@GetMapping("/getUserBy")
	public Object getUserBy(HttpServletRequest request) {
		return RestTemplateUtil.get(MyUrl.GET_USER_BY+request.getQueryString(), LoginInterceptor.getUser().getId());
	}

	public static void main(String[] args) {
		Object o = RestTemplateUtil.get(MyUrl.GET_ALL_UNIT, LoginInterceptor.getUser().getId());
		System.err.println(JSON.toJSONString(o));
	}
}
