package com.nicchagil.module.ec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicchagil.module.ec.vo.OrderDisplayVo;
import com.nicchagil.orm.entity.EcOrder;
import com.nicchagil.orm.entity.EcOrderExample;
import com.nicchagil.orm.mapper.ext.EcOrderExtMapper;

@Service
public class EcOrderService {
	
	@Autowired
	private EcOrderExtMapper mapper;

	/**
	 * 添加记录
	 */
	public int insert(EcOrder record) {
		int num = this.mapper.insert(record);
		return num;
	}
	
	/**
	 * 查询全部订单记录
	 */
	public List<OrderDisplayVo> getList() {
		List<OrderDisplayVo> list = this.mapper.getList();
		return list;
	}
	
	/**
	 * 删除所有订单记录
	 */
	public int deleteAll() {
		EcOrderExample e = new EcOrderExample();
		int num = this.mapper.deleteByExample(e);
		return num;
	}
	
}
