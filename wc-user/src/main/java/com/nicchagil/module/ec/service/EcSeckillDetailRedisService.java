package com.nicchagil.module.ec.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.nicchagil.dubbo.model.ec.SeckillOrderReqVo;
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
	public String check(SeckillOrderReqVo reqVo) {
		if (reqVo == null || reqVo.getGoodsId() == null || reqVo.getNum() == null) {
			return "请传入正确的参数";
		}
		reqVo.setOrderDate(new Date()); // 记录请求时刻的时间（避免后面请求阻塞、开启事务后再取得当前时间较不准确）
		
		Long goodsId = reqVo.getGoodsId();
		Long num = reqVo.getNum();
		
		/* 校验秒杀开始时间 */
		SeckillDisplayVo vo = new SeckillDisplayVo();
		vo.setGoodsId(goodsId);
		vo.setNum(num);
		
		String startTimeKey = this.ecSeckillDetailRedisSyncService.getStartTimeKey(vo);
		
		String startDateStr = this.stringRedisTemplate.opsForValue().get(startTimeKey);
		Date startDate = DateTimeUtils.parse(startDateStr, DateTimeUtils.STANDARD_DATE_TIME);
		if (startDate == null) {
			return "Redis数据异常，缺少秒杀开始时间";
		}
		
		if (startDate.after(new Date())) {
			return "秒杀活动还没开始，请稍后再试";
		}
		
		/* 校验库存 */
		String goodsNumKey = this.ecSeckillDetailRedisSyncService.getGoodsNumKey(vo);
		Long currentNum = this.redisLongTemplate.opsForValue().get(goodsNumKey);
		
		if (currentNum == null) {
			return "数据异常，缺少秒杀商品库存数量";
		}
		
		if (currentNum.longValue() < num.longValue()) {
			return "商品已卖完，请下次再来";
		}
		
		return null;
	}
	
}
