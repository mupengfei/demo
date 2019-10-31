package com.mrgan.html.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GetTestVideos {
	public static void main(String[] args) {
		File file = new File("E://videotest//stocksy.txt");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] names = line.split("/");
				HttpUtil.download(line, "E://videotest//imgs", names[names.length - 1]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
