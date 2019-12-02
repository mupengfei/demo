package com.mrgan.layui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class AdminController {
	private static final Logger logger = LogManager.getLogger(AdminController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		logger.error("this is index");
		return "index";
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("789456"));
		// $2a$10$h.cMz2AmkBbkuUKnFY837uCB3/JZgtJUV7BBUnaGWUCrMk/FF4nX.

		// String encodedPassword =
		// "$2y$10$wO./FnpCf8NkqjXbgV5ZT.juYia/Ts4Fz6ZBZgfWObmOjlAQwKtfO";
		String encodedPassword = "$2a$10$heJxtqAGBUg0nTTF2I7WSef2Fg/sBUx4hyNiGHLfR9DqZQZOKsRgK";

		boolean bMatch = bCryptPasswordEncoder.matches("789456", encodedPassword);
		System.out.println(bMatch);
	}
}
