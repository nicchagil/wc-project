package com.nicchagil.util.random;

import java.util.Random;

import org.junit.Test;

public class RandomUtils {

	/**
	 * 获取指定值以内的随机数
	 */
	public static int getRandomNumber(int limit) {
		return new Random().nextInt(limit);
	}
	
	@Test
	public void getRandomNumberTest() {
		for (int i = 0; i < 100; i++) {
			System.out.println(RandomUtils.getRandomNumber(5));
		}
	}

}
