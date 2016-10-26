package com.mrgan.springboot.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrgan.springboot.model.Customer;

@RestController
@RequestMapping("/rest")
public class RestTestController {
	@RequestMapping(value = { "/date/{date}" })
	public Customer dateTest(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return new Customer("firstName", "lastName", date);
	}
}
