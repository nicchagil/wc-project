package com.nicchagil.module.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.module.ec.service.EcSeckillService;
import com.nicchagil.module.ec.vo.SeckillDisplayVo;
import com.nicchagil.util.exception.StandardResponse;

@RestController
@RequestMapping("/ec/seckill")
public class EcSeckillController {
	
	@Autowired
	private EcSeckillService service;
	
	/**
	 * 添加秒杀
	 */
	@GetMapping("/add")
	public StandardResponse<String> add(Long goodsId, Long goodsNum) {
		this.service.add(goodsId, goodsNum);
		return StandardResponse.getSuccessResponse("OK");
	}
	
	@GetMapping("/getList")
	public List<SeckillDisplayVo> getList() {
		List<SeckillDisplayVo> list = this.service.getList();
		return list;
	}

	/**
	 * 删除秒杀
	 */
	@GetMapping("/deleteById")
	public StandardResponse<String> deleteById(Long id) {
		this.service.deleteById(id);
		return StandardResponse.getSuccessResponse("OK");
	}

}
