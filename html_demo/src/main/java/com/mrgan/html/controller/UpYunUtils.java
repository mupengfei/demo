package com.mrgan.html.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class UpYunUtils {
	private static String UTF8 = "UTF-8";
	public int time = 600;

	public void setTime(int second) {
		this.time = second;
	}

	/**
	 * 生成 token 签名
	 * 
	 * @return
	 */
	public String getSign(String key, String data) {
		byte[] keyBytes = md5(key).getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
		Mac mac;
		try {
			mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			return new String(encodeBase64Ex(rawHmac));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static byte[] encodeBase64Ex(byte[] src) {
		// urlsafe version is not supported in version 1.4 or lower.
		byte[] b64 = Base64.encodeBase64(src);

		for (int i = 0; i < b64.length; i++) {
			if (b64[i] == '/') {
				b64[i] = '_';
			} else if (b64[i] == '+') {
				b64[i] = '-';
			}
		}
		return b64;
	}

	/**
	 * 生成 token 签名
	 * 
	 * @return
	 */
	public String toToken(String key, String path) {
		long eTime = new Date().getTime() / 1000 + time;
		String sign = md5(key + "&" + eTime + "&" + path).substring(12, 20) + eTime;
		return sign;
	}

	/**
	 * 对字符串进行 MD5 加密
	 * 
	 * @param strSrc
	 * @return
	 */
	public static String md5(String strSrc) {
		String result = "";
		byte[] temp = null;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			temp = md5.digest(strSrc.getBytes(UTF8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < temp.length; i++) {
			result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
		}
		return result;
	}

	public static String getSignatureHmacSHA1(byte[] data, String key) {
		byte[] keyBytes = key.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
		Mac mac;
		StringBuffer sb = new StringBuffer();
		try {
			mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data);

			for (byte b : rawHmac) {
				sb.append(byteToHexString(b));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String byteToHexString(byte src) {
		StringBuilder stringBuilder = new StringBuilder("");
		// for (int i = 0; i < src.length; i++) {
		int v = src & 0xFF;
		String hv = Integer.toHexString(v);
		if (hv.length() < 2) {
			stringBuilder.append(0);
		}
		stringBuilder.append(hv);
		// }
		return stringBuilder.toString();
	}
}