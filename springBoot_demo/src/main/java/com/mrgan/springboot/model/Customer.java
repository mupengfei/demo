package com.mrgan.springboot.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mrgan.springboot.utils.CustomDateSerializer;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
	private String firstName;
	private String lastName;
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date dateName;
}
