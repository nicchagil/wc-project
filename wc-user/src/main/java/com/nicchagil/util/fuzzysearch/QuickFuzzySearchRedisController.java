package com.nicchagil.util.fuzzysearch;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.util.redis.springredistemplate.RedisBasicService;

@RestController
@RequestMapping("/fuzzysearch/redis")
public class QuickFuzzySearchRedisController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QuickFuzzySearchRedisService quickFuzzySearchRedisService;
	
	@Autowired
	private RedisBasicService redisBasicService;
	
	/**
	 * 根据KEY的表达式删除记录
	 */
	@GetMapping("/delete") // /fuzzysearch/redis/delete
	public Long delete(String keyPattern) {
		return this.redisBasicService.delete(keyPattern);
	}
	
	/**
	 * 构建索引
	 */
	@GetMapping("/buildIndex") // /fuzzysearch/redis/buildIndex
	public void buildIndex() {
		this.quickFuzzySearchRedisService.buildIndexWithTransaction();
	}
	
	/**
	 * 查询索引
	 */
	@GetMapping("/queryAllIndex") // /fuzzysearch/redis/queryAllIndex
	public Map<String, Set<String>> queryAllIndex() {
		return this.quickFuzzySearchRedisService.queryAllIndex();
	}
	
	/**
	 * 查询索引
	 */
	@GetMapping("/fuzzySearch") // /fuzzysearch/redis/fuzzySearch
	public Set<String> fuzzySearch(String key) {
		return this.quickFuzzySearchRedisService.fuzzySearch(key);
	}
	
}
