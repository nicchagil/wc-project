package com.nicchagil.module.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.module.ec.service.RedisService;
import com.nicchagil.module.ec.vo.RedisKeyValueVo;
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
	
	/**
	 * 查询Redis的所有数据
	 */
	@GetMapping("/getAllRedisKeyValueVo")
	public StandardResponse<List<RedisKeyValueVo>> getAllRedisKeyValueVo() {
		List<RedisKeyValueVo> list = this.redisService.getAllRedisKeyValueVo();
		return StandardResponse.getSuccessResponse(list);
	}
	
}
