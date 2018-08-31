package com.nicchagil.util.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class LambdaTest {
	
	@Test
	public void filterTestx1() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		Stream<String> stream = list.stream();
		Predicate<String> predicate = s -> s != null && s.equals("1"); // 断言，Predicate。入参对象，出参boolean，用于判别一个对象
		System.out.println("count -> " + stream.filter(predicate).count()); // 过滤并统计
	}
	
	@Test
	public void filterTestx2() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		long count = list.stream().filter(s -> s != null && s.equals("1")).count();
		System.out.println("count -> " + count);
	}

	@Test
	public void filterTestx3() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		List<String> resultList = list.stream().filter(s -> s != null && s.equals("1")).collect(Collectors.toList());
		System.out.println("resultList -> " + resultList);
	}

}
