package com.mrgan.layui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

@SpringBootApplication
public class Application {
	private static Logger logger = LogManager.getLogger(Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Gson gson() {
		return new GsonBuilder().create();
	}

	// public void addInterceptors(InterceptorRegistry registry) {
	// registry.addInterceptor(new HandlerInterceptorAdapter() {
	// @Override
	// public void postHandle(HttpServletRequest request, HttpServletResponse
	// response, Object handler,
	// ModelAndView modelAndView) throws Exception {
	// // TODO Auto-generated method stub
	// System.out.println(request.getContextPath());
	// System.out.println("2interceptor====");
	// super.postHandle(request, response, handler, modelAndView);
	// }
	//
	// @Override
	// public boolean preHandle(HttpServletRequest request, HttpServletResponse
	// response, Object handler)
	// throws Exception {
	// System.out.println(request.getContextPath());
	// System.out.println("interceptor====");
	// return false;
	// }
	// }).addPathPatterns("/pages/*");
	// }
}
