package com.nicchagil.module.ec.service;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

@Service
public class RedisService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String KEY_SPLITER = ":";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 获取键匹配的所有键
	 */
	public Set<String> keys(String pattern) {
		Set<String> keys = this.stringRedisTemplate.execute(new RedisCallback<Set<String>>() {

			@Override
			public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
				Set<String> keys = Sets.newHashSet();
				
				Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match("*").count(1000).build());
				while (cursor.hasNext()) {
					keys.add(new String(cursor.next()));
				}
				
				return keys;
			}
			
		});
		
		return keys;
	}
	
	/**
	 * 根据匹配符删除Redis记录
	 */
	public void deleteAll() {
		this.deleteByKeyPattern("*");
	}
	
	/**
	 * 根据匹配符删除Redis记录
	 */
	public void deleteByKeyPattern(String pattern) {
		Set<String> keys = this.keys(pattern);
		this.removeKeys(keys);
	}
	
	/**
	 * 删除指定的KEY
	 */
	public void removeKeys(Set<String> keys) {
		if (CollectionUtils.isEmpty(keys)) {
			return;
		}
		
		this.stringRedisTemplate.delete(keys);
	}
	
}
