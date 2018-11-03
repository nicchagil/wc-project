package com.nicchagil.module.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.module.ec.service.EcOrderService;
import com.nicchagil.module.ec.vo.OrderDisplayVo;
import com.nicchagil.util.exception.StandardResponse;

@RestController
@RequestMapping("/ec/order")
public class EcOrderController {
	
	@Autowired
	private EcOrderService service;

	/**
	 * 查询全部订单
	 */
	@PostMapping("/getList")
	public StandardResponse<List<OrderDisplayVo>> getList() {
		List<OrderDisplayVo> list = this.service.getList();
		return StandardResponse.getSuccessResponse(list);
	}
	
	/**
	 * 删除全部订单
	 */
	@PostMapping("/deleteAll")
	public StandardResponse<String> deleteAll() {
		this.service.deleteAll();
		return StandardResponse.getSuccessResponse("删除成功");
	}

}
