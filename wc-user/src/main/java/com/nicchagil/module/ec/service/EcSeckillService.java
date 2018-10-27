package com.nicchagil.module.ec.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicchagil.module.ec.vo.SeckillDisplayVo;
import com.nicchagil.orm.entity.EcSeckillDetail;
import com.nicchagil.orm.mapper.EcSeckillDetailMapper;
import com.nicchagil.orm.mapper.ext.EcSeckillExtMapper;
import com.nicchagil.util.DataTimeUtils;

@Service
public class EcSeckillService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EcSeckillExtMapper mapper;
	
	@Autowired
	private EcSeckillDetailMapper ecSeckillDetailMapper;
	
	/**
	 * 添加秒杀商品
	 */
	public void add(Long goodsId, Long num) {
		/* 插入秒杀明细表 */
		EcSeckillDetail detail = new EcSeckillDetail();
		detail.setGoodsId(goodsId);
		detail.setNum(num);
		detail.setStartTime(DataTimeUtils.add(new Date(), DataTimeUtils.SECOND * 5));
		detail.setEndTime(DataTimeUtils.add(new Date(), DataTimeUtils.DAY * 5));
		this.ecSeckillDetailMapper.insert(detail);
		this.logger.info("EcSeckillDetail : {}", detail);
		return;
	}

	/**
	 * 查询全部秒杀信息
	 */
	public List<SeckillDisplayVo> getList() {
		List<SeckillDisplayVo> list = this.mapper.getList();
		return list;
	}
	
	/**
	 * 根据ID删除记录 
	 */
	public void deleteById(Long id) {
		this.ecSeckillDetailMapper.deleteByPrimaryKey(id);
	}

}
