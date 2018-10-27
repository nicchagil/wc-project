package com.nicchagil.module.ec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicchagil.orm.entity.EcGoods;
import com.nicchagil.orm.entity.EcGoodsExample;
import com.nicchagil.orm.mapper.EcGoodsMapper;

@Service
public class EcGoodsService {
	
	@Autowired
	private EcGoodsMapper mapper;

	/**
	 * 查询全部商品
	 */
	public List<EcGoods> getList() {
		EcGoodsExample e = new EcGoodsExample();
		e.setOrderByClause("id desc");
		
		List<EcGoods> list = this.mapper.selectByExample(e);
		return list;
	}

}
