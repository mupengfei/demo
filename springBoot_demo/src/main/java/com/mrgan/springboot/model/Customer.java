package com.mrgan.springboot.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mrgan.springboot.utils.CustomDateSerializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
	private String firstName;
	@NotNull
	private String lastName;
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date dateName;
}
