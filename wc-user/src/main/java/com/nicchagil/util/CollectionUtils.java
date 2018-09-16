package com.nicchagil.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionUtils {
	
	private static Logger logger = LoggerFactory.getLogger(CollectionUtils.class);

	/**
	 * 计算在list1存在，而在list2不存在的记录的集合
	 */
	public static <T> List<T> subtract(List<T> list1, List<T> list2) {
		// 转换为Set
		Set<T> set = list2.stream().collect(Collectors.toSet());
		
		// 计算在list1存在，而在list2不存在的记录的集合
		List<T> resultList = list1.stream().filter(i -> !set.contains(i)).collect(Collectors.toList());
		return resultList;
	}
	
	/**
	 * 计算交集，在list1存在，并且在list2也存在的记录的集合
	 */
	public static <T> List<T> intersect(List<T> list1, List<T> list2) {
		// 转换为Set
		Set<T> set = list2.stream().collect(Collectors.toSet());
				
		// 计算在list1存在，而在list2不存在的记录的集合
		List<T> resultList = list1.stream().filter(i -> set.contains(i)).collect(Collectors.toList());
		return resultList;
	}
	
	/**
	 * 计算并集，在list1和list2的记录合并，并且去重的集合
	 */
	public static <T> List<T> union(List<T> list1, List<T> list2) {
		Set<T> set = Stream.of(list1, list2).flatMap(i -> i.stream()).collect(Collectors.toSet());
		List<T> resultList = set.stream().collect(Collectors.toList());
		
		return resultList;
	}
	
	@Test
	public void subtractTest() {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
		List<Integer> list2 = Lists.newArrayList(1, 5);
		
		List<Integer> resultList = CollectionUtils.subtract(list1, list2);
		
		this.logger.info("list1 -> {}", list1);
		this.logger.info("resultList -> {}", resultList);
	}
	
	@Test
	public void intersectTest() {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
		List<Integer> list2 = Lists.newArrayList(1, 5);
		
		List<Integer> resultList = CollectionUtils.intersect(list1, list2);
		
		this.logger.info("list1 -> {}", list1);
		this.logger.info("resultList -> {}", resultList);
	}
	
	@Test
	public void unionTest() {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
		List<Integer> list2 = Lists.newArrayList(1, 5, 6);
		
		List<Integer> resultList = CollectionUtils.union(list1, list2);
		
		this.logger.info("list1 -> {}", list1);
		this.logger.info("resultList -> {}", resultList);
	}

}
