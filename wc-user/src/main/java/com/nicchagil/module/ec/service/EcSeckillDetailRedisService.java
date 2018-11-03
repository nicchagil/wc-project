package com.nicchagil.module.ec.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nicchagil.module.ec.vo.SeckillOrderReqVo;
import com.nicchagil.module.ec.vo.SeckillDisplayVo;
import com.nicchagil.util.datetime.DateTimeUtils;

@Service
public class EcSeckillDetailRedisService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String KEY_SPLITER = ":";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Resource(name = "redisLongTemplate")
	private RedisTemplate<String, Long> redisLongTemplate;
	
	@Autowired
	private EcSeckillDetailRedisSyncService ecSeckillDetailRedisSyncService;
	
	@Autowired
	private EcOrderService ecOrderService;

	@Autowired
	private RedisService redisService;
	
	/**
	 * 检查库存
	 */
	@Transactional
	public void check(SeckillOrderReqVo reqVo) {
		Assert.notNull(reqVo, "请传入正确的参数");
		Assert.notNull(reqVo.getGoodsId(), "请传入正确的参数");
		Assert.notNull(reqVo.getNum(), "请传入正确的参数");
		
		Long goodsId = reqVo.getGoodsId();
		Long num = reqVo.getNum();
		
		SeckillDisplayVo vo = new SeckillDisplayVo();
		vo.setGoodsId(goodsId);
		vo.setNum(num);
		
		String startTimeKey = this.ecSeckillDetailRedisSyncService.getStartTimeKey(vo);
		
		String startDateStr = this.stringRedisTemplate.opsForValue().get(startTimeKey);
		Date startDate = DateTimeUtils.parse(startDateStr, DateTimeUtils.STANDARD_DATE_TIME);
		Assert.notNull(startDate, "Redis数据异常，缺少秒杀开始时间");
		
		if (startDate.after(new Date())) {
			throw new RuntimeException("秒杀活动还没开始，请稍后再试");
		}
		
		// 减去库存，提交订单
		this.ecOrderService.doOrder(goodsId, num);
	}
	
}
