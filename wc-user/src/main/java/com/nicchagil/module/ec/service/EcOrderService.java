package com.nicchagil.module.ec.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.nicchagil.module.ec.vo.OrderDisplayVo;
import com.nicchagil.module.ec.vo.SeckillDisplayVo;
import com.nicchagil.orm.entity.EcOrder;
import com.nicchagil.orm.entity.EcOrderExample;
import com.nicchagil.orm.mapper.ext.EcOrderExtMapper;
import com.nicchagil.util.random.RandomStringGenerater;

@Service
public class EcOrderService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EcOrderExtMapper mapper;
	
	@Autowired
	private EcSeckillDetailRedisSyncService ecSeckillDetailRedisSyncService;
	
	@Resource(name = "redisLongTemplate")
	private RedisTemplate<String, Long> redisLongTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private EcSeckillDetailService ecSeckillDetailService;
	
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
		Long substractNum = num * -1;
		this.logger.info("substract num : {}", substractNum);
		Long result = this.stringRedisTemplate.opsForValue().increment(goodsNumKey, substractNum);
		Assert.isTrue(result.longValue() >= 0, "商品已卖完，请下次再来");
		this.logger.info("substract result : {}", result);
		
		if (result.longValue() < 0) {
			throw new RuntimeException("商品已卖完，请下次再来");
		}
		
		/* MySQL减库存 */
		this.logger.info("substract : {}, {}", goodsId, num);
		int substractRecordNum = this.ecSeckillDetailService.substract(goodsId, num);
		Assert.isTrue(substractRecordNum == 1, "MySQL减去库存失败");
		
		/* 添加库存 */
		EcOrder order = new EcOrder();
		order.setUserId(Long.valueOf(RandomStringGenerater.generateRandomString(6, RandomStringGenerater.NUMBER_10))); // 随机生成数字
		order.setGoodsId(goodsId);
		order.setNum(num);
		Date currentDate = new Date();
		order.setCreateTime(currentDate);
		order.setUpdateTime(currentDate);
		this.insert(order);
	}

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
