package com.nicchagil.util.desctranslator;

import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

public class UserIdToUserNameVoSingleFieldTranslator extends AbstractVoSingleFieldTranslator<User> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, String> getValueDescMapByValueList(List<String> valueList) {
		// 模拟查询数据库得到结果
		Map<String, String> map = Maps.newHashMap();
		valueList.forEach(i -> map.put(i, i + " desc..."));
		
		return map;
	}

	@Test
	public void translateTest() {
		UserIdToUserNameVoSingleFieldTranslator t = new UserIdToUserNameVoSingleFieldTranslator();
		String value = "1";
		String desc = t.translate(value);
		logger.info("{} -> {}", value, desc);
	}
	
	@Test
	public void translateVoTest() {
		User user = new User();
		user.setUserId("1");
		
		UserIdToUserNameVoSingleFieldTranslator t = new UserIdToUserNameVoSingleFieldTranslator();
		t.translateVo(user, "userId");
		
		logger.info("result -> {}", user);
	}

	@Test
	public void translateVoListTest() {
		User user1 = new User();
		user1.setUserId("1");
		
		User user2 = new User();
		user2.setUserId("2");
		
		List<User> userList = Lists.newArrayList(user1, user2);
		
		UserIdToUserNameVoSingleFieldTranslator t = new UserIdToUserNameVoSingleFieldTranslator();
		t.translateVoList(userList, "userId");
		
		logger.info("result -> {}", userList);
	}
	
}
