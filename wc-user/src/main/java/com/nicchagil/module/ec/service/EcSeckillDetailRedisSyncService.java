package com.nicchagil.module.ec.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.nicchagil.module.ec.vo.SeckillDisplayVo;
import com.nicchagil.util.datetime.DateTimeUtils;

@Service
public class EcSeckillDetailRedisSyncService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String KEY_SPLITER = ":";
	
	@Autowired
	private EcSeckillDetailService ecSeckillDetailService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private EcOrderService ecOrderService;
	
	/**
	 * 秒杀数据同步到Redis
	 */
	public void syncToRedis() {
		List<SeckillDisplayVo> list = this.ecSeckillDetailService.getList();
		
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
			
			/* 校验开始时间格式 */
			Date startTime = DateTimeUtils.parse(vo.getStartTime(), DateTimeUtils.STANDARD_DATE_TIME);
			if (startTime == null) {
				this.logger.info("秒杀详情的开始时间数据异常：{}", vo);
				continue;
			}
			
			String key = this.getStartTimeKey(vo);
			
			/* 删除指定的KEY */
			Set<String> keys = this.stringRedisTemplate.keys(key);
			if (CollectionUtils.isNotEmpty(keys)) {
				this.removeKeys(keys);
			}
			
			/* 写入开始时间 */
			this.stringRedisTemplate.opsForValue().set(key, vo.getStartTime());
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
			Set<String> keys = this.stringRedisTemplate.keys(key);
			if (CollectionUtils.isNotEmpty(keys)) {
				this.removeKeys(keys);
			}
			
			/* 写入开始时间 */
			this.stringRedisTemplate.opsForValue().set(key, vo.getNum().toString());
			this.logger.info("写入完毕{} : {}", key, vo.getNum());
		}
	}
	
	/**
	 * 开始时间的KEY
	 */
	public String getStartTimeKey(SeckillDisplayVo vo) {
		String key = new StringBuffer().append("StartTime").append(KEY_SPLITER).append(vo.getGoodsId()).toString();
		return key;
	}
	
	/**
	 * 商品数量的KEY
	 */
	public String getGoodsNumKey(SeckillDisplayVo vo) {
		String key = new StringBuffer().append("GoodsNum").append(KEY_SPLITER).append(vo.getGoodsId()).toString();
		return key;
	}
	
	/**
	 * 删除指定的KEY
	 */
	public void removeKeys(Set<String> keys) {
		if (CollectionUtils.isEmpty(keys)) {
			return;
		}
		
		this.stringRedisTemplate.delete(keys);
	}
	
}
