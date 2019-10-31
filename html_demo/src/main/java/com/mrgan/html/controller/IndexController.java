package com.mrgan.html.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/demo")
/**
 * 
 * @author Lenovo
 *
 */
public class IndexController {
	private static final Logger logger = LogManager.getLogger(IndexController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, RedirectAttributes redirectAttributes) {
		return "index";
	}

	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public String video(Model model, RedirectAttributes redirectAttributes) {
		return "videopreview";
	}

	@RequestMapping(value = "/up", method = RequestMethod.GET)
	public String up(Model model, RedirectAttributes redirectAttributes) {
		return "upyunupload";
	}

	@RequestMapping(value = "/upduan", method = RequestMethod.GET)
	public String upduan(Model model, RedirectAttributes redirectAttributes) {
		return "upduan";
	}

	@RequestMapping(value = "/rest", method = RequestMethod.GET)
	public String rest(Model model, RedirectAttributes redirectAttributes) {
		return "rest";
	}

	@RequestMapping(value = "/sign")
	@ResponseBody
	public String sign(@RequestParam(name = "path") String path, @RequestParam(name = "method") String method,
			Model model, RedirectAttributes redirectAttributes) {
		UpYunUtils yun = new UpYunUtils();
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String date = sdf.format(cd.getTime());
		System.out.println(date);
		// String date = new Date().toString();
		String data = method + "&/streamd" + path + "&" + date;
		System.out.println(data);

		return "{\"Authorization\" : \"UPYUN mupengfei:" + yun.getSign("mu789456", data) + "\" ,\"x-date\" : \"" + date
				+ "\"}";
	}

	public static void main(String[] args) {
		UpYunUtils yun = new UpYunUtils();
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String date = sdf.format(cd.getTime());
		// String date = new Date().toString();
		String requestBody = "service=streamd&notify_url=www.baidu.com&source=/test/3.mov&tasks=WwogICAgewogICAgICAgICJ0eXBlIjogInZpZGVvIiwKICAgICAgICAiYXZvcHRzIjogIi92Yi81MDAwL3MvMTkyMCoxMDgwL2FzLzEvci8yNCIsCiAgICAgICAgInJldHVybl9pbmZvIjogdHJ1ZSwKICAgICAgICAic2F2ZV9hcyI6ICIvdGVzdC8zMTA4MC5tcDQiCiAgICB9Cl0=&accept=json";
		String md5RequestBody = yun.md5(requestBody);
		System.out.println(md5RequestBody);
		String data = "POST&/pretreatment/" + "&" + date + "&" + md5RequestBody;
		System.out.println(data);

		System.out.println("{\"Authorization\" : \"UPYUN mupengfei:" + yun.getSign("mu789456", data)
				+ "\" ,\"x-date\" : \"" + date + "\"}");
	}
}
