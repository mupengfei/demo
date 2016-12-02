package com.mrgan.springboot.controller;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrgan.springboot.Application;
import com.mrgan.springboot.model.Customer;
import com.mrgan.springboot.model.User;

@RestController
@RequestMapping("/rest")
public class RestTestController {
	private static Logger logger = LogManager.getLogger(RestTestController.class
			.getName());
	
	@RequestMapping(value = { "/date/{date}" })
	public Customer dateTest(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return new Customer("firstName", "lastName", date);
	}

	@RequestMapping(value = { "/check" })
	public String check(
			@Valid @Min(value = 0, message = "num必须大于等于0") @RequestParam Integer num) {
		// System.out.println(error.getErrorCount());
		logger.info("Hello world 你好 hhh " + System.currentTimeMillis());
		return num + "";
	}

	@RequestMapping(value = { "/binding" })
	public String bindingTest(@Valid User user, BindingResult bindingResult,
			@Valid Customer customer, BindingResult bindingResult2) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getErrorCount());
			return "error";
		}
		if (bindingResult2.hasErrors()) {
			System.out.println(bindingResult2.getErrorCount());
			return bindingResult2.getFieldErrors().get(0).getDefaultMessage();
		}
		System.out.println(user);
		System.out.println(customer);
		return "ok";
	}
}
