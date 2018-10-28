package com.nicchagil.module.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicchagil.orm.entity.EcOrder;
import com.nicchagil.orm.mapper.EcOrderMapper;

@Service
public class EcOrderService {
	
	@Autowired
	private EcOrderMapper mapper;

	/**
	 * 添加记录
	 */
	public int insert(EcOrder record) {
		int num = this.mapper.insert(record);
		return num;
	}
	
}
