package com.nicchagil.test.jdk.lambda;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.util.objectcan.User;
import com.nicchagil.util.objectcan.UserConstants;

public class ListToMapTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void getIdUserMap() {
		Map<Integer, User> map1 = UserConstants.USER_LISTS.stream().collect(Collectors.toMap(User::getId, i -> i));
		this.logger.info("map1 : {}", map1);
		
		Map<Integer, User> map2 = UserConstants.USER_LISTS.stream().collect(Collectors.toMap(User::getId, Function.identity()));
		this.logger.info("map2 : {}", map2);
	}
	
	/**
	 * 重复KEY的处理
	 */
	@Test
	public void repeatKeyTest() {
		List<User> REPEAT_USER_LISTS = Lists.newArrayList(
				new User(2, "Hello World"),
				new User(1, "Hello Kitty"),
				new User(1, "Hello Java")
		);
		
		Map<Integer, User> map2 = REPEAT_USER_LISTS.stream().collect(Collectors.toMap(User::getId, Function.identity(), 
				(key1, key2) -> key2));
		this.logger.info("map2 : {}", map2);
	}
	
	@Test
	public void mapTest() {
		Map<Integer, User> map1 = UserConstants.USER_LISTS.stream().collect(Collectors.toMap(User::getId, Function.identity(), (key1, key2) -> key2, LinkedHashMap::new));
		this.logger.info("map1 class : {}", map1.getClass());
		
		Map<Integer, User> map2 = UserConstants.USER_LISTS.stream().collect(Collectors.toMap(User::getId, Function.identity(), (key1, key2) -> key2, TreeMap::new));
		this.logger.info("map2 class : {}", map2.getClass());
	}
	
}
