package com.mrgan.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
	private String name;
	private int tel;
	private double height;
	private String firstName;
}
