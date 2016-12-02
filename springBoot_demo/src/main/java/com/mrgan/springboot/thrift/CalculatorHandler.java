package com.mrgan.springboot.thrift;

import java.util.HashMap;

import org.apache.thrift.TException;

public class CalculatorHandler implements Calculator.Iface {

	@Override
	public void ping() throws TException {
		// TODO Auto-generated method stub
		System.out.println("ping");
	}

	@Override
	public int add(int num1, int num2) throws TException {
		System.out.println(num1 + " " + num2);
		return num1 + num2;
	}

}
