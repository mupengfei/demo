package com.mrgan.html.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HttpUtil {
	private static Logger logger = LogManager.getLogger(HttpUtil.class.getName());

	public static final int cache = 1024 * 1024;

	// public static boolean download(String url, String filepath, String
	// filename) {
	// return CommandExec.execCommand("wget -O " + filepath + File.separator+
	// filename + " " + url);
	// }

	// wget -O /data/src/cptn/storage/app/public/wordpress.zip
	// http://cptnupload.kksmg.com/20170607165703-0.38416.mpg

	public static boolean download(String url, String filepath, String filename) {
		int retryTimes = 3;
		int nowTimes = 0;
		while (nowTimes < retryTimes) {
			InputStream is = null;
			FileOutputStream fileout = null;
			try {
				CloseableHttpClient client = HttpClientBuilder.create().build();
				RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(60000)
						.setConnectTimeout(60000).setSocketTimeout(60000).build();
				HttpGet httpget = new HttpGet(url);
				httpget.setConfig(requestConfig);
				HttpResponse response = client.execute(httpget);

				HttpEntity entity = response.getEntity();
				// if (entity.getContentEncoding().getValue().equals("404")) {
				// logger.error(url + " 该地址 not found");
				// return false;
				// }
				long length = entity.getContentLength();
				is = entity.getContent();
				File file = new File(filepath + File.separator + filename);
				if (!file.exists())
					file.createNewFile();
				fileout = new FileOutputStream(file);
				/**
				 * 根据实际运行效果 设置缓冲区大小
				 */
				byte[] buffer = new byte[cache];
				int ch = 0;
				long fileLength = 0;
				while ((ch = is.read(buffer)) != -1) {
					if (fileLength == length)
						break;
					fileout.write(buffer, 0, ch);
					fileLength = fileLength + ch;
					if (fileLength == length)
						break;
				}
				if (fileLength != length)
					return false;
				fileout.flush();
				return true;
			} catch (Exception e) {
				System.out.println(filename);
				e.printStackTrace();
				logger.error(e.getLocalizedMessage());
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fileout != null) {
					try {
						fileout.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			nowTimes++;
			continue;
		}
		return false;
	}
}
