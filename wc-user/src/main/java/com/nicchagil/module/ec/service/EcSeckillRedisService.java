package com.nicchagil.module.ec.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.nicchagil.module.ec.vo.SeckillDisplayVo;
import com.nicchagil.util.datetime.DateTimeUtils;

@Service
public class EcSeckillRedisService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String KEY_SPLITER = ":";
	
	@Autowired
	private EcSeckillService ecSeckillService;
	
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Date> redisStringTemplate;
	
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Long> redisLongTemplate;
	
	/**
	 * 检查库存
	 */
	public void check(Long goodsId, Long num) {
		if (goodsId == null || num == null) {
			throw new RuntimeException("请传入正确的参数");
		}
		
		SeckillDisplayVo vo = new SeckillDisplayVo();
		vo.setGoodsId(goodsId);
		vo.setNum(num);
		
		String startTimeKey = this.getStartTimeKey(vo);
		
		Date startDate = this.redisStringTemplate.opsForValue().get(startTimeKey);
		if (startDate == null) {
			throw new RuntimeException("数据异常，缺少秒杀开始时间");
		}
		
		if (startDate.before(new Date())) {
			throw new RuntimeException("秒杀活动还没开始，请稍后再试");
		}
		
		String goodsNumKey = this.getGoodsNumKey(vo);
		
		Long currentNum = this.redisLongTemplate.opsForValue().get(goodsNumKey);
		
		if (currentNum == null) {
			throw new RuntimeException("数据异常，缺少秒杀商品库存数量");
		}
		
		if (currentNum.longValue() < num.longValue()) {
			throw new RuntimeException("商品已卖完，请下次再来");
		}
	}
	
	/**
	 * 秒杀数据同步到Redis
	 */
	public void syncToRedis() {
		List<SeckillDisplayVo> list = this.ecSeckillService.getList();
		
		if (CollectionUtils.isEmpty(list)) {
			this.logger.info("无秒杀数据，无须同步缓存");
			return;
		}
		
		/* 写入Redis：[StartTime]:SeckillId:GoodsId : startDate */
		for (SeckillDisplayVo vo : list) {
			if (vo == null || vo.getSeckillId() == null || vo.getGoodsId() == null || vo.getStartTime() == null) {
				this.logger.info("秒杀详情数据异常：{}", vo);
				continue;
			}
			
			/* 转换开始时间格式 */
			Date startTime = DateTimeUtils.parse(vo.getStartTime(), DateTimeUtils.STANDARD_DATE_TIME);
			if (startTime == null) {
				this.logger.info("秒杀详情的开始时间数据异常：{}", vo);
				continue;
			}
			
			String key = this.getStartTimeKey(vo);
			
			/* 删除指定的KEY */
			Set<String> keys = this.redisStringTemplate.keys(key);
			if (CollectionUtils.isNotEmpty(keys)) {
				this.removeKeys(keys);
			}
			
			/* 写入开始时间 */
			this.redisStringTemplate.opsForValue().set(key, startTime);
			this.logger.info("写入完毕{} : {}", key, vo.getStartTime());
		}
		
		/* 写入Redis：[GoodsNum]:SeckillId:GoodsId : startDate */
		for (SeckillDisplayVo vo : list) {
			if (vo == null || vo.getSeckillId() == null || vo.getGoodsId() == null || vo.getNum() == null) {
				this.logger.info("秒杀详情数据异常：{}", vo);
				continue;
			}
			
			/* 转换开始时间格式 */
			Date startTime = DateTimeUtils.parse(vo.getStartTime(), DateTimeUtils.STANDARD_DATE_TIME);
			if (startTime == null) {
				this.logger.info("秒杀详情的开始时间数据异常：{}", vo);
				continue;
			}
			
			String key = this.getGoodsNumKey(vo);
			
			/* 删除指定的KEY */
			Set<String> keys = this.redisStringTemplate.keys(key);
			if (CollectionUtils.isNotEmpty(keys)) {
				this.removeKeys(keys);
			}
			
			/* 写入开始时间 */
			// this.redisLongTemplate.opsForValue().set(key, vo.getNum());
			this.logger.info("写入完毕{} : {}", key, vo.getNum());
		}
	}
	
	/**
	 * 开始时间的KEY
	 */
	public String getStartTimeKey(SeckillDisplayVo vo) {
		String key = new StringBuffer().append("StartTime").append(KEY_SPLITER).append(vo.getSeckillId()).append(KEY_SPLITER).append(vo.getGoodsId()).toString();
		return key;
	}
	
	/**
	 * 商品数量的KEY
	 */
	public String getGoodsNumKey(SeckillDisplayVo vo) {
		String key = new StringBuffer().append("GoodsNum").append(KEY_SPLITER).append(vo.getSeckillId()).append(KEY_SPLITER).append(vo.getGoodsId()).toString();
		return key;
	}
	
	/**
	 * 删除指定的KEY
	 */
	public void removeKeys(Set<String> keys) {
		if (CollectionUtils.isEmpty(keys)) {
			return;
		}
		
		this.redisStringTemplate.delete(keys);
	}
	
}
