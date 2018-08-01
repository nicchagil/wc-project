package com.nicchagil.util.redis.springredistemplate;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

}
