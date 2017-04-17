package com.mrgan.mongodb.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "customertest")
@Data
public class Customer {

	@Id
	private String firstName;
	private String lastName;
	private Date dateName;

	public Customer() {
	}

	public Customer(String firstName, String lastName, Date dateName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateName = dateName;
	}

	@Override
	public String toString() {
		return String.format("Customer[ firstName='%s', lastName='%s', dateName='%s']", firstName, lastName, dateName);
	}

}
