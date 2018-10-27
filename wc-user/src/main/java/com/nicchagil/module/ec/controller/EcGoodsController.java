package com.nicchagil.module.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.module.ec.service.EcGoodsService;
import com.nicchagil.orm.entity.EcGoods;

@RestController
@RequestMapping("/ec/goods")
public class EcGoodsController {
	
	@Autowired
	private EcGoodsService service;

	/**
	 * 查询全部商品
	 */
	@GetMapping("/getList")
	public List<EcGoods> getList() {
		List<EcGoods> list = this.service.getList();
		return list;
	}

}
