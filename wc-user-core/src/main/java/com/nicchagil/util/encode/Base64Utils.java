package com.nicchagil.util.encode;

import java.util.Base64;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64Utils {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static String encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static byte[] decode(String s) {
		return Base64.getDecoder().decode(s);
	}
	
	@Test
	public void encodeAndDecode() {
		String source = "hello world";
		
		String targetBase64 = Base64Utils.encode(source.getBytes());
		byte[] targetBytes = Base64Utils.decode(targetBase64);
		String targetString = new String(targetBytes);
		
		this.logger.info("source : {}", source);
		this.logger.info("target : {}", targetString);
	}

}
