package com.byxx.zcbuy.config;

import com.byxx.zcbuy.utils.NpResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 聂鹏
 * @description: controller层异常捕捉,返回值 responseBody修改
 * @create 2019/05/23
 */
@RestControllerAdvice
public class MyResponseBody {
	/**
	 * ExceptionHandler    //处理所有异常
	 * ExceptionHandler(NullPointerException.class)  //处理专门的异常
	 * ExceptionHandler(value={RuntimeException.class,MyRuntimeException.class}) //处理多个异常
	 */

	@ExceptionHandler(Exception.class)
	public NpResult exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response){
		e.printStackTrace();
		return NpResult.error("服务器异常!");
	}
}
