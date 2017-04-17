package com.mrgan.mongodb.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mrgan.mongodb.model.Command;
import com.mrgan.mongodb.model.Customer;
import com.mrgan.mongodb.repository.CommandDao;

@RestController
@RequestMapping("/command")
public class CommandController {

	@Autowired
	private CommandDao commandDao;

	@RequestMapping(value = "/getAllCommand", method = RequestMethod.GET)
	public List<Command> getAllCommand() {
		return commandDao.findAll();
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Command findOneCommand(@PathParam("id") String id) {
		return commandDao.findOne(id);
	}
}
