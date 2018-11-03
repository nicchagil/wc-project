package com.nicchagil.util.random;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomStringGenerater {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final char[] LOWERCASE_26 = new char[26];
	public static final char[] UPPERCASE_26 = new char[26];
	public static final char[] LETTER_AND_SYMBOL = new char[94];
	public static final char[] NUMBER_10 = new char[10];
	
	static {
		int lowercase26ArrayIndex = 0;
		for (int i = 97; i <= 122; i++) {
			LOWERCASE_26[lowercase26ArrayIndex++] = (char)i;
		}
		
		int uppercase26ArrayIndex = 0;
		for (int i = 65; i <= 90; i++) {
			UPPERCASE_26[uppercase26ArrayIndex++] = (char)i;
		}
		
		int letterAndSymbolArrayIndex = 0;
		for (int i = 33; i <= 126; i++) {
			LETTER_AND_SYMBOL[letterAndSymbolArrayIndex++] = (char)i;
		}
		
		for (int i = 0; i <= 9; i++) {
			NUMBER_10[i] = String.valueOf(i).charAt(0);
		}
	}
	
	@Test
	public void print26Test() {
		this.logger.info("LOWERCASE_26 : {}", LOWERCASE_26);
		this.logger.info("UPPERCASE_26 : {}", UPPERCASE_26);
		this.logger.info("LETTER_AND_SYMBOL : {}", LETTER_AND_SYMBOL);
		this.logger.info("NUMBER_10 : {}", NUMBER_10);
	}
	
	/**
	 * 根据长度和字符范围生成随机字符串
	 */
	public static String generateRandomString(int length, char[] chars) {
		if (length == 0) {
			return StringUtils.EMPTY;
		}
		
		int limit = chars.length;
		
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= length; i++) {
			int index = RandomUtils.getRandomNumber(limit);
			sb.append(chars[index]);
		}
		
		return sb.toString();
	}
	

	@Test
	public void generateRandomStringTest() {
		for (int i = 0; i < 10; i++) {
			this.logger.info("RandomString : {}", RandomStringGenerater.generateRandomString(8, RandomStringGenerater.NUMBER_10));
		}
	}

}
