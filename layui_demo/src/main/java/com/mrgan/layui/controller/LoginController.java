package com.mrgan.layui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	private static final Logger logger = LogManager
			.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(String username, String password, Model model) {
		logger.error("login");
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("password"));
		// $2a$10$h.cMz2AmkBbkuUKnFY837uCB3/JZgtJUV7BBUnaGWUCrMk/FF4nX.
		// String encodedPassword =
		// "$2y$10$wO./FnpCf8NkqjXbgV5ZT.juYia/Ts4Fz6ZBZgfWObmOjlAQwKtfO";
		String encodedPassword = "$2a$10$wO./FnpCf8NkqjXbgV5ZT.juYia/Ts4Fz6ZBZgfWObmOjlAQwKtfO";
		boolean bMatch = bCryptPasswordEncoder
				.matches("broly", encodedPassword);
		System.out.println(bMatch);
		return "index";
	}
}
