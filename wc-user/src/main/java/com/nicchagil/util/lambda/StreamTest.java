package com.nicchagil.util.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamTest {
	
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
