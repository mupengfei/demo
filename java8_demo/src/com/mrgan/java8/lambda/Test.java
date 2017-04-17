package com.mrgan.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Test {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("sdsdasad");
		list.add("a");
		list.add("sd");
		list.add("22");
		list.add("ds");
		list.add("dsa232");
		Stream<String> stream = list.stream();
		Stream<String> tmp = stream.filter(s -> s.length() > 3);
		list.add("dsa232");
		System.out.println(tmp);
		stream.close();
	}
}
