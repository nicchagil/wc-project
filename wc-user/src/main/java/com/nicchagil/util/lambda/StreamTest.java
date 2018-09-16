package com.nicchagil.util.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	@Test
	public void streamMethodTest() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3", "3"});
		
		Stream<String> stream = list.stream();
		boolean allMatch = stream.allMatch(i -> i != null && i.equals("1"));
		this.logger.info("allMatch -> {}", allMatch);
		
		stream = list.stream();
		boolean anyMatch = stream.anyMatch(i -> i != null && i.equals("1"));
		this.logger.info("anyMatch -> {}", anyMatch);
		
		stream = list.stream();
		Optional<String> findFirst = stream.findFirst();
		this.logger.info("findFirst.get() -> {}", findFirst.get());
		
		stream = list.stream();
		Set<String> set = stream.collect(Collectors.toSet());
		this.logger.info("set -> {}", set);
		
		stream = list.stream();
		StringBuilder stringBuilder = stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
		this.logger.info("stringBuilder -> {}", stringBuilder);
		
		stream = list.stream();
		long count = stream.count();
		this.logger.info("count -> {}", count);
		
		stream = list.stream();
		Set<String> set2 = stream.distinct().collect(Collectors.toSet());
		this.logger.info("set2 -> {}", set2);
		
		stream = list.stream();
		List<String> list2 = stream.filter(i -> i != null && i.equals("1")).collect(Collectors.toList());
		this.logger.info("list2 -> {}", list2);
		
		stream = list.stream();
		Optional<String> findAny = stream.findAny();
		this.logger.info("findAny -> {}", findAny);
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
