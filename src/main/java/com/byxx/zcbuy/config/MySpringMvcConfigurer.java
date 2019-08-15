package com.byxx.zcbuy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MySpringMvcConfigurer implements WebMvcConfigurer {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
			//指定要拦截的请求 /** 表示拦截所有请求
			.addPathPatterns("/**")
			//排除不需要拦截的请求路径
			.excludePathPatterns("/static/**","/", "/doLogin","/t/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//静态资源映射，直接把static 下的资源映射到 /static目录
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
}
