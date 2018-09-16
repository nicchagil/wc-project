package com.nicchagil.util.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SceneTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 过滤
	 */
	@Test
	public void predicateTestx1() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		List<String> resultList = list.stream().filter(s -> s != null && s.equals("1")).collect(Collectors.toList());
		this.logger.info("resultList -> " + resultList);
	}
	
	/**
	 * 转换类型
	 */
	@Test
	public void functionTestx1() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		List<Integer> resultList = list.stream().map(i -> Integer.valueOf(i)).collect(Collectors.toList());
		this.logger.info("resultList -> " + resultList);
	}

	/**
	 * 转换类型
	 */
	@Test
	public void mergeTestx1() {
		List<String> list1 = Arrays.asList(new String[] {"1", "2", "3"});
		List<String> list2 = Arrays.asList(new String[] {"4", "5", "6"});
		
		// 这个不是我想要的结果
		/*
		List<List<String>> resultList1 = Stream.of(list1, list2).collect(Collectors.toList());
		this.logger.info("resultList1 -> " + resultList1);
		*/
		
		List<String> resultList2 = Stream.of(list1, list2).flatMap(i -> i.stream()).collect(Collectors.toList());
		this.logger.info("resultList2 -> " + resultList2);
	}
	
}
