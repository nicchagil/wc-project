package com.nicchagil.util.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamTest {
	
	@Test
	public void streamMethodTest() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		Stream<String> stream = list.stream();
		stream.allMatch(i -> i != null && i.equals("1"));
		stream.anyMatch(i -> i != null && i.equals("1"));
		stream.findFirst();
		stream.collect(Collectors.toSet());
		stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
		stream.count();
		stream.distinct().collect(Collectors.toSet());
		stream.filter(i -> i != null && i.equals("1"));
		stream.findAny();
	}
	
	@Test
	public void countTest() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		long count = list.stream().count();
		System.out.println("count -> " + count);
	}
	
	@Test
	public void collectTest() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		List<String> resultList = list.stream().collect(Collectors.toList());
		System.out.println("resultList -> " + resultList);
	}

}
