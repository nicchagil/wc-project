package com.nicchagil.module.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.module.ec.service.RedisService;
import com.nicchagil.util.exception.StandardResponse;

@RestController
@RequestMapping("/redis")
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 根据匹配符删除Redis记录
	 */
	@PostMapping("/deleteAll")
	public StandardResponse<String> deleteAll() {
		this.redisService.deleteAll();
		return StandardResponse.getSuccessResponse("删除全部Redis记录完成");
	}
	
}
