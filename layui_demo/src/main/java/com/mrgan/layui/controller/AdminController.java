package com.mrgan.layui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	private static final Logger logger = LogManager.getLogger(AdminController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		logger.error("this is index");
		return "index";
	}
}
