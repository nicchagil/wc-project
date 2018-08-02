package com.nicchagil.util.quickfuzzysearch;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fuzzysearch/redis")
public class QuickFuzzySearchRedisController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QuickFuzzySearchRedisService quickFuzzySearchRedisService;
	
	/**
	 * 构建索引
	 */
	@GetMapping("/buildIndex") // /fuzzysearch/redis/buildIndex
	public void buildIndex() {
		this.quickFuzzySearchRedisService.buildIndex();
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
