package com.nicchagil.util.redis.springredistemplate;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import com.nicchagil.util.test.BaseSpringBootTest;

public class RedisTemplateTest extends BaseSpringBootTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Test
	public void setAndGetTest() {
		ValueOperations<String, String> valueOperations = this.redisTemplate.opsForValue();
		
		valueOperations.set("hello", "world");
		this.logger.info("result : {}", valueOperations.get("hello"));
	}
	
	@Test
	public void hashSetAndGetTest() {
		HashOperations<String, String, String> hashOperations = this.redisTemplate.opsForHash();
		
		Map<String, String> map = Maps.newHashMap();
		map.put("name", "Nick Huang");
		map.put("age", "20");
		
		hashOperations.putAll("id:123", map);
		
		this.logger.info("name : {}", hashOperations.get("id:123", "name"));
		this.logger.info("age : {}", hashOperations.get("id:123", "age"));
		
		this.logger.info("result : {}", hashOperations.multiGet("id:123", Lists.newArrayList("name", "age")));
	}
	
	@Test
	public void unionAndStoreTest() {
		/* 准备数据 */
		SetOperations<String, String> setOperations = this.redisTemplate.opsForSet();
		
		setOperations.add("dept:member:001", "nick", "kid", "vivi");
		setOperations.add("dept:member:002", "sissi", "yuki", "nick");
		setOperations.add("dept:member:003", "eason", "yuki", "kid");
		
		/* 测试：并集、存储、过时 */
		setOperations.unionAndStore("dept:member:001", Lists.newArrayList("dept:member:002", "dept:member:003"), 
				"dept:member:001-003");
		this.redisTemplate.expire("dept:member:001-003", 5, TimeUnit.SECONDS);
		this.logger.info("expire : {}", this.redisTemplate.getExpire("dept:member:001-003"));
		this.logger.info("result : {}", setOperations.members("dept:member:001-003"));
	}
	
	@Test
	public void intersectTest() {
		/* 准备数据 */
		SetOperations<String, String> setOperations = this.redisTemplate.opsForSet();
		
		setOperations.add("dept:member:001", "nick", "kid", "vivi");
		setOperations.add("dept:member:002", "sissi", "yuki", "nick");
		setOperations.add("dept:member:003", "eason", "yuki", "kid");
		
		/* 测试：交集 */
		Set<String> set = setOperations.intersect("dept:member:001", Lists.newArrayList("dept:member:002", "dept:member:003"));
		this.logger.info("result : {}", set);
	}

}
