package com.nicchagil.test.jdk.lambda;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.util.objectcan.User;

public class ListGroupByToMapTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void groupByTest() {
		List<User> REPEAT_USER_LISTS = Lists.newArrayList(
				new User(2, "Hello World"),
				new User(1, "Hello Kitty"),
				new User(1, "Hello Java")
		);
		
		Map<Integer, List<User>> map = REPEAT_USER_LISTS.stream().collect(Collectors.groupingBy(User::getId));
		this.logger.info("map : {}", map);
		this.logger.info("map list class : {}", map.get(1).getClass());
	}
	
	@Test
	public void groupByTest2() {
		List<User> REPEAT_USER_LISTS = Lists.newArrayList(
				new User(2, "Hello World"),
				new User(1, "Hello Kitty"),
				new User(1, "Hello Java")
				);
		
		
		Map<Integer, Long> map = REPEAT_USER_LISTS.stream().collect(Collectors.groupingBy(User::getId, Collectors.counting()));
		this.logger.info("map : {}", map);
		this.logger.info("map list class : {}", map.get(1).getClass());
	}
	
	@Test
	public void groupByTest3() {
		List<User> REPEAT_USER_LISTS = Lists.newArrayList(
				new User(2, "Hello World"),
				new User(1, "Hello Kitty"),
				new User(1, "Hello Java")
		);
		
		Map<Integer, LinkedList<User>> map = REPEAT_USER_LISTS.stream().collect(Collectors.groupingBy(User::getId, Collectors.toCollection(LinkedList::new)));
		this.logger.info("map : {}", map);
		this.logger.info("map list class : {}", map.get(1).getClass());
	}
	
}
