package com.nicchagil.util.redis.springredistemplate;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisBasicService {
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 根据KEY的表达式删除记录
	 */
	public long delete(String keyPattern) {
		if (StringUtils.isBlank(keyPattern)) {
			return 0;
		}
		
		Set<String> keySet = this.redisTemplate.keys(keyPattern);
		if (CollectionUtils.isEmpty(keySet)) {
			return 0;
		}
		
		long counter = 0;
		for (String key : keySet) {
			this.redisTemplate.delete(key);
			counter++;
		}
		
		return counter;
	}

}
