package com.nicchagil.module.ec.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicchagil.module.ec.vo.SeckillDisplayVo;
import com.nicchagil.orm.entity.EcSeckillDetail;
import com.nicchagil.orm.entity.EcSeckillDetailExample;
import com.nicchagil.orm.mapper.EcSeckillDetailMapper;
import com.nicchagil.orm.mapper.ext.EcSeckillExtMapper;
import com.nicchagil.util.DataTimeUtils;

@Service
public class EcSeckillDetailService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EcSeckillDetailMapper mapper;
	
	@Autowired
	private EcSeckillExtMapper ecSeckillExtMapper;
	
	/**
	 * 添加秒杀商品
	 */
	public void add(Long goodsId, Long num) {
		EcSeckillDetail detail = this.getByGoodsId(goodsId);
		
		if (detail == null) {
			/* 插入秒杀明细表 */
			EcSeckillDetail insertDetail = new EcSeckillDetail();
			insertDetail.setGoodsId(goodsId);
			insertDetail.setNum(num);
			insertDetail.setStartTime(DataTimeUtils.add(new Date(), DataTimeUtils.SECOND * 5));
			insertDetail.setEndTime(DataTimeUtils.add(new Date(), DataTimeUtils.DAY * 5));
			this.mapper.insert(insertDetail);
			this.logger.info("插入记录 : {}", insertDetail);
		} else {
			detail.setNum(num);
			detail.setStartTime(DataTimeUtils.add(new Date(), DataTimeUtils.SECOND * 5));
			detail.setEndTime(DataTimeUtils.add(new Date(), DataTimeUtils.DAY * 5));
			this.mapper.updateByPrimaryKey(detail);
			this.logger.info("更新记录 : {}", detail);
		}
		
		return;
	}
	
	/**
	 * 根据商品ID查询记录
	 */
	public EcSeckillDetail getByGoodsId(Long goodsId) {
		EcSeckillDetailExample e = new EcSeckillDetailExample();
		e.createCriteria().andGoodsIdEqualTo(goodsId);
		
		List<EcSeckillDetail> list = this.mapper.selectByExample(e);
		
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		
		return list.get(0);
	}
	
	/**
	 * 查询全部秒杀信息
	 */
	public List<SeckillDisplayVo> getList() {
		List<SeckillDisplayVo> list = this.ecSeckillExtMapper.getList();
		return list;
	}
	
	/**
	 * 根据ID删除记录 
	 */
	public void deleteById(Long id) {
		this.mapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 减去库存
	 */
	public int substract(Long goodsId, Long num) {
		int updateNum = this.ecSeckillExtMapper.substract(goodsId, num);
		return updateNum;
	}
	
}
