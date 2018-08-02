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
	 * 构建索引（TODO 事务未完全写好）
	 */
	public void buildIndexWithTransaction() {
		/* 开始事务 */
		this.redisTemplate.multi();
		this.logger.info("开始事务 OK");
		
		this.buildIndex();
		
		/* 执行事务 */
		List<Object> list = this.redisTemplate.exec();
		this.logger.info("执行事务 OK");
	}

	/**
	 * 构建索引
	 */
	public void buildIndex() {
		this.logger.info("开始构建索引");
		
		Set<String> keySet = this.redisTemplate.keys(PREFIX_KEY + "*");
		for (String key : keySet) {
			this.redisTemplate.delete(key);
		}
		this.logger.info("删除原来的KEY OK");
		
		List<User> userList = Lists.newArrayList();
		for (int i = 0; i < 100000; i++) {
			if (Integer.valueOf(66666).equals(i)) {
				userList.add(new User(i, "nick"));
			}
			if (Integer.valueOf(77777).equals(i)) {
				userList.add(new User(i, "viki"));
			}
			
			userList.add(new User(i, String.valueOf(i)));
		}
		this.logger.info("初始化模拟数据OK");
		
		EveryWordTokenizer tokenizer = new EveryWordTokenizer();
		SetOperations<String, String> setOperations = this.redisTemplate.opsForSet();
		
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			List<String> indexList = tokenizer.participle(user.getName());
			
			for (String index : indexList) {
				setOperations.add(PREFIX_KEY + index, String.valueOf(user.getName()));
			}
			
			if (i % 1000 == 0) {
				this.logger.info("进度：{}", i);
			}
		}
		
		this.logger.info("结束构建索引");
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
