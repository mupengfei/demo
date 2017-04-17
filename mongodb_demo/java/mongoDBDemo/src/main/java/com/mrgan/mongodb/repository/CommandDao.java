package com.mrgan.mongodb.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mrgan.mongodb.model.Command;

public interface CommandDao extends MongoRepository<Command, String> {
	long countByExitValueNot(int exitValue);

	long countByExitValue(int exitValue);

	List<Command> findByExitValueNot(int exitValue);

	List<Command> findByExitValue(int exitValue);

	List<Command> findByExitValueAndStartTimeBetween(int exitValue, long startTimeSt, long startTimeEd, Sort sort);

}