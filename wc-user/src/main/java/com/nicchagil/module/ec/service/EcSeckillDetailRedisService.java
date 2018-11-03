package com.nicchagil.module.ec.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.util.Assert;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.nicchagil.module.ec.vo.SeckillBuyReqVo;
import com.nicchagil.module.ec.vo.SeckillDisplayVo;
import com.nicchagil.module.ec.vo.SeckillRedisDisplayVo;
import com.nicchagil.orm.entity.EcOrder;
import com.nicchagil.util.datetime.DateTimeUtils;

@Service
public class EcSeckillDetailRedisService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String KEY_SPLITER = ":";
	
	@Autowired
	private EcSeckillDetailService ecSeckillDetailService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Resource(name = "redisLongTemplate")
	private RedisTemplate<String, Long> redisLongTemplate;
	
	@Autowired
	private EcSeckillDetailRedisSyncService ecSeckillDetailRedisSyncService;

	@Autowired
	private EcOrderService ecOrderService;
	
	/**
	 * 检查库存
	 */
	public void check(SeckillBuyReqVo reqVo) {
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
		this.substract(goodsId, num);
	}
	
	/**
	 * 查询Redis的所有数据
	 */
	public List<SeckillRedisDisplayVo> getSeckillRedisDisplayVo() {
		Set<String> keys = this.stringRedisTemplate.execute(new RedisCallback<Set<String>>() {

			@Override
			public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
				Set<String> keys = Sets.newHashSet();
				
				Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match("*").count(1000).build());
				while (cursor.hasNext()) {
					keys.add(new String(cursor.next()));
				}
				
				return keys;
			}
			
		});
		this.logger.info("redis keys : {}", keys);
		
		if (CollectionUtils.isEmpty(keys)) {
			return null;
		}
		
		List<String> keyList = Lists.newArrayList(keys);
		Collections.sort(keyList);
		
		List<SeckillRedisDisplayVo> voList = Lists.newArrayList();
		for (String key : keyList) {
			Object value = this.stringRedisTemplate.opsForValue().get(key);
			
			SeckillRedisDisplayVo vo = new SeckillRedisDisplayVo();
			vo.setKey(key);
			if (value != null) {
				vo.setValue(value.toString());
			}
			voList.add(vo);
		}
		
		return voList;
	}
	
	/**
	 * 减去库存
	 */
	public void substract(Long goodsId, Long num) {
		SeckillDisplayVo vo = new SeckillDisplayVo();
		vo.setGoodsId(goodsId);
		
		String goodsNumKey = this.ecSeckillDetailRedisSyncService.getGoodsNumKey(vo);
		
		/* 校验库存 */
		Long currentNum = this.redisLongTemplate.opsForValue().get(goodsNumKey);
		
		if (currentNum == null) {
			throw new RuntimeException("数据异常，缺少秒杀商品库存数量");
		}
		
		if (currentNum.longValue() < num.longValue()) {
			throw new RuntimeException("商品已卖完，请下次再来");
		}
		
		/* Redis原子操作减库存 */
		Long result = this.stringRedisTemplate.opsForValue().increment(goodsNumKey, num * -1);
		this.logger.info("substract result : {}", result);
		
		if (result.longValue() < 0) {
			throw new RuntimeException("商品已卖完，请下次再来");
		}
		
		/* MySQL减库存 */
		this.logger.info("substract : {}, {}", goodsId, currentNum);
		int substractRecordNum = this.ecSeckillDetailService.substract(goodsId, currentNum);
		Assert.isTrue(substractRecordNum == 1, "减去库存失败");
		
		/* 添加库存 */
		EcOrder order = new EcOrder();
		order.setUserId(999999L);
		order.setGoodsId(goodsId);
		order.setNum(num);
		this.ecOrderService.insert(order);
	}
	
}
