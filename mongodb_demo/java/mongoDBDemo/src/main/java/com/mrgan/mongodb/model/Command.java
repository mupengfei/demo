package com.mrgan.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "kk_microservice_command")
@Data
public class Command {
	@Id
	private String id;
	private String command;
	private String errorMessage;
	private String infoMessage;
	private int exitValue;
	private long startTime;
	private long endTime;
	private String status;
}
