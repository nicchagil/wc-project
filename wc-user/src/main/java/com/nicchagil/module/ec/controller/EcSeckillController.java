package com.nicchagil.module.ec.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.dubbo.model.ec.SeckillOrderReqVo;
import com.nicchagil.module.ec.service.EcSeckillDetailRedisService;
import com.nicchagil.module.ec.service.EcSeckillDetailRedisSyncService;
import com.nicchagil.module.ec.service.EcSeckillDetailService;
import com.nicchagil.module.ec.service.EcSeckillOrderService;
import com.nicchagil.module.ec.vo.SeckillAddReqVo;
import com.nicchagil.module.ec.vo.SeckillDisplayVo;
import com.nicchagil.util.exception.ExceptionCodeEnum;
import com.nicchagil.util.exception.StandardResponse;

@RestController
@RequestMapping("/ec/seckill")
public class EcSeckillController {
	
	@Autowired
	private EcSeckillDetailService ecSeckillDetailService;
	
	@Autowired
	private EcSeckillDetailRedisSyncService ecSeckillDetailRedisSyncService;
	
	@Autowired
	private EcSeckillDetailRedisService ecSeckillRedisService;
	
	@Autowired
	private EcSeckillOrderService ecSeckillOrderService;
	
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
	@PostMapping("/deleteById")
	public StandardResponse<String> deleteById(Long id) {
		this.ecSeckillDetailService.deleteById(id);
		return StandardResponse.getSuccessResponse("OK");
	}


	/**
	 * 同步秒杀到Redis
	 */
	@PostMapping("/syncToRedis")
	public StandardResponse<String> syncToRedis() {
		this.ecSeckillDetailRedisSyncService.syncToRedis();
		return StandardResponse.getSuccessResponse("OK");
	}
	
	/**
	 * 购买
	 */
	@PostMapping("/buy")
	public StandardResponse<String> buy(@RequestBody SeckillOrderReqVo reqVo) {
		String msg = this.ecSeckillOrderService.order(reqVo);
		
		if (StringUtils.isBlank(msg)) {
			return StandardResponse.getSuccessResponse("OK");
		} else {
			return StandardResponse.getDiyResponse(ExceptionCodeEnum.MSG_00001.name(), msg, null);
		}
	}
	
}
