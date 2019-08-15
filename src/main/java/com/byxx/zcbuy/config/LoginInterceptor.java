package com.byxx.zcbuy.config;

import com.alibaba.fastjson.JSON;
import com.byxx.zcbuy.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	//定义一个线程域，存放登录的对象
	private static final ThreadLocal<User> thread = new ThreadLocal<>();
    //调用目标方法之前被拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    Object userMsg = request.getSession().getAttribute("userMsg");
	    System.err.println(request.getRequestURI());
	    if( userMsg == null) {
		    //没有登录过
		    request.setAttribute("msg", "没有登录！");
		    request.getRequestDispatcher("/").forward(request, response);
		    return false;
	    }
	    User user = JSON.parseObject(JSON.toJSONString(userMsg), User.class);
	    thread.set(user);
	    return true;
    }
	//页面渲染之后调用，一般用于资源清理操作
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//过滤器完成后，从线程域中删除用户信息
		thread.remove();
	}

	//获取登陆用户
	public static User getUser() {
		return thread.get();
	}

}
