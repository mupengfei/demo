package com.mrgan.mongodb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mrgan.mongodb.model.Customer;
import com.mrgan.mongodb.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = "/saveCustomer/{firstName}/{lastName}", method = RequestMethod.GET)
	public Customer saveCustomer(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		return customerRepository.save(new Customer(firstName, lastName,
				new Date()));
	}

	@RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET)
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	public String deleteAllCustomer() {
		customerRepository.save(new Customer("firstName", "lastName",
				new Date()));
		customerRepository.deleteAll();
		return "1";
	}

	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public Customer findOneCustomer() {
		return customerRepository.findOne("firstName");
	}

	// @RequestMapping(value = "/findComplex", method = RequestMethod.GET)
	// public Customer findOneCustomerComplex() {
	// /**
	// * OR
	// */
	// BasicDBList orList = new BasicDBList(); // 用于记录
	// orList.add(new BasicDBObject("firstName", 1));
	// orList.add(new BasicDBObject("firstName", 2));
	// BasicDBObject orDBObject = new BasicDBObject("$or", orList);
	// // BasicDBList andList = new BasicDBList();
	// // andList.add(new BasicDBObject("name", name));
	// // andList.add(orDBObject);
	// // BasicDBObject andDBObject = new BasicDBObject("$and", andList);
	// return customerRepository.findOne(orDBObject);
	// }
}
