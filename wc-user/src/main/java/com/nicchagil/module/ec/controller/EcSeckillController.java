package com.nicchagil.module.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.module.ec.service.EcSeckillDetailRedisService;
import com.nicchagil.module.ec.service.EcSeckillDetailService;
import com.nicchagil.module.ec.vo.SeckillAddReqVo;
import com.nicchagil.module.ec.vo.SeckillDisplayVo;
import com.nicchagil.module.ec.vo.SeckillRedisDisplayVo;
import com.nicchagil.util.exception.StandardResponse;

@RestController
@RequestMapping("/ec/seckill")
public class EcSeckillController {
	
	@Autowired
	private EcSeckillDetailService ecSeckillDetailService;
	
	@Autowired
	private EcSeckillDetailRedisService ecSeckillRedisService;
	
	/**
	 * 添加秒杀
	 */
	@PostMapping("/add")
	public StandardResponse<String> add(@RequestBody SeckillAddReqVo seckillAddReqVo) {
		this.ecSeckillDetailService.add(seckillAddReqVo);
		return StandardResponse.getSuccessResponse("OK");
	}
	
	@GetMapping("/getList")
	public StandardResponse<List<SeckillDisplayVo>> getList() {
		List<SeckillDisplayVo> list = this.ecSeckillDetailService.getList();
		return StandardResponse.getSuccessResponse(list);
	}

	/**
	 * 删除秒杀
	 */
	@GetMapping("/deleteById")
	public StandardResponse<String> deleteById(Long id) {
		this.ecSeckillDetailService.deleteById(id);
		return StandardResponse.getSuccessResponse("OK");
	}


	/**
	 * 同步秒杀到Redis
	 */
	@GetMapping("/syncToRedis")
	public StandardResponse<String> syncToRedis() {
		this.ecSeckillRedisService.syncToRedis();
		return StandardResponse.getSuccessResponse("OK");
	}
	
	/**
	 * 购买
	 */
	@GetMapping("/buy")
	public StandardResponse<String> buy(Long goodsId, Long goodsNum) {
		this.ecSeckillRedisService.check(goodsId, goodsNum);
		return StandardResponse.getSuccessResponse("OK");
	}
	
	/**
	 * 查询Redis的所有数据
	 */
	@GetMapping("/getSeckillRedisDisplayVo")
	public StandardResponse<List<SeckillRedisDisplayVo>> getSeckillRedisDisplayVo() {
		List<SeckillRedisDisplayVo> list = this.ecSeckillRedisService.getSeckillRedisDisplayVo();
		return StandardResponse.getSuccessResponse(list);
	}
	
}
