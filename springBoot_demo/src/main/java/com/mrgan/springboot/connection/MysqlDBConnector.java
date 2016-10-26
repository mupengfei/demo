package com.mrgan.springboot.connection;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 生产数据库
 */
@Component
@Profile("mysql")
public class MysqlDBConnector implements DBConnector {

	@Override
	public void configure() {

		System.out.println("mysql");

	}

}