package com.nicchagil.module.ec.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
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
import com.nicchagil.module.ec.vo.RedisKeyValueVo;

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
	 * 查询Redis的所有数据
	 */
	public List<RedisKeyValueVo> getAllRedisKeyValueVo() {
		Set<String> keys = this.keys("*");
		this.logger.info("redis keys : {}", keys);
		
		if (CollectionUtils.isEmpty(keys)) {
			return null;
		}
		
		List<String> keyList = Lists.newArrayList(keys);
		Collections.sort(keyList);
		
		List<RedisKeyValueVo> voList = Lists.newArrayList();
		for (String key : keyList) {
			Object value = this.stringRedisTemplate.opsForValue().get(key);
			
			RedisKeyValueVo vo = new RedisKeyValueVo();
			vo.setKey(key);
			if (value != null) {
				vo.setValue(value.toString());
			}
			voList.add(vo);
		}
		
		return voList;
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
