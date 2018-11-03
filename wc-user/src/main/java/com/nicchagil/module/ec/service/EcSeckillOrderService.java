package com.nicchagil.module.ec.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicchagil.module.ec.vo.SeckillOrderReqVo;

@Service
public class EcSeckillOrderService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EcSeckillDetailRedisService ecSeckillDetailRedisService;
	
	@Autowired
	private EcOrderService ecOrderService;
	
	/**
	 * 处理秒杀订单
	 */
	public String order(SeckillOrderReqVo reqVo) {
		/* 校验秒杀库存是否足够 */
		String checkMsg = this.ecSeckillDetailRedisService.check(reqVo);
		
		/* 消息不为空则表示校验有异常 */
		if (StringUtils.isNotBlank(checkMsg)) {
			return checkMsg;
		}
		
		/* 处理订单 */
		this.ecOrderService.doOrder(reqVo);
		return null;
	}
	
}
