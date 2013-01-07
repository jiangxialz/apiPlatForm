package com.sharehome.platform.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MD5Util {
	public static Logger log = Logger.getLogger(MD5Util.class);
	
	public static String make(String str){
		return md5(str);
	}
	
	private static String md5(String str){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			str = new sun.misc.BASE64Encoder().encode(md5.digest(str.getBytes("utf8")));
		} catch (NoSuchAlgorithmException e) {
			log.info(e);
		}catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		return str;
	}
	
	public static void main(String[] args) {
		System.out.println(make("admin"));
	}
	
}
