package com.mrgan.mongodb.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customertest")
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
		return String.format(
				"Customer[ firstName='%s', lastName='%s', dateName='%s']",
				firstName, lastName, dateName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateName() {
		return dateName;
	}

	public void setDateName(Date dateName) {
		this.dateName = dateName;
	}

}
