package com.mrgan.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrgan.springboot.config.ConnectionSettings;
import com.mrgan.springboot.connection.DBConnector;

@RestController
@RequestMapping("/config")
public class ConfigController {
	@Value("${connection.remoteAddress}")
	private String address;

	@Autowired
	private Environment ev;

	@Autowired
	private ConnectionSettings conn;

	@Autowired
	private DBConnector connector;

	@RequestMapping(value = { "/", "" })
	public String config1(@Value("${connection.username}") String name) {
		String password = ev.getProperty("connection.print");
		return "hello " + address + ":" + conn.getUsername() + "  " + password;
	}

	@RequestMapping(value = { "/", "sqlname" })
	public String config2(@Value("${sql.name}") String name) {
		connector.configure();
		return "now sql name is " + name;
	}
}
