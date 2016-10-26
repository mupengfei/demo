package com.mrgan.springboot.connection;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 测试数据库
 */
@Component
@Profile("test")
public class TestDBConnector implements DBConnector {

	@Override
	public void configure() {

		System.out.println("testdb");

	}
}