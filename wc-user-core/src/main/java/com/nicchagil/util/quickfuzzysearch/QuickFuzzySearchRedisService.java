package com.nicchagil.util.quickfuzzysearch;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import com.nicchagil.util.index.tokenizer.EveryWordTokenizer;

@Service
public class QuickFuzzySearchRedisService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String PREFIX_KEY = "USER-FUZZY-INDEX:";
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 构建索引
	 */
	public void buildIndex() {
		List<User> userList = Lists.newArrayList();
		for (int i = 0; i < 1000000; i++) {
			userList.add(new User(i, String.valueOf(i)));
		}
		
		EveryWordTokenizer tokenizer = new EveryWordTokenizer();
		SetOperations<String, String> setOperations = this.redisTemplate.opsForSet();
		
		for (User user : userList) {
			List<String> indexList = tokenizer.participle(user.getName());
			
			for (String index : indexList) {
				setOperations.add(PREFIX_KEY + index, String.valueOf(user.getId()));
			}
		}
		
		this.logger.info("ok..");
	}
	
	/**
	 * 查询索引
	 */
	public Map<String, Set<String>> queryAllIndex() {
		Set<String> keySet = this.redisTemplate.keys(PREFIX_KEY + "*");
		
		Map<String, Set<String>> map = Maps.newHashMap();
		for (String key : keySet) {
			map.put(key, this.redisTemplate.opsForSet().members(key));
		}
		
		this.logger.info("map : {}", map);
		return map;
		
	}
	
	/**
	 * 查询索引
	 */
	public Set<String> fuzzySearch(String key) {
		return this.redisTemplate.opsForSet().members(PREFIX_KEY + key);
	}
	
	public class User {
		private Integer id;
		private String name;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public User(Integer id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
	}
	
}
