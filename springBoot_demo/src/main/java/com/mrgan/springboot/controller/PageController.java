package com.mrgan.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class PageController {
	@RequestMapping("/hello")
	public ModelAndView hello(ModelAndView model) {
		System.out.println("hello");
		model.setViewName("HelloThymeleaf");
		model.addObject("message", "Hello1");
		return model;
	}
}
