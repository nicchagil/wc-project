package com.nicchagil.util.desctranslator;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserIdToUserNameVoMultiFieldTranslator extends AbstractVoListMultiFieldTranslator<User> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public TranslatorSetVo<User> getTranslatorSetVo() {
		return new TranslatorSetVo<User>().add("userId", new UserIdToUserNameVoSingleFieldTranslator())
				.add("creatorId", new UserIdToUserNameVoSingleFieldTranslator());
	}
	
	@Test
	public void translateVoListTest() {
		User user1 = new User();
		user1.setUserId("1");
		user1.setCreatorId("1");
		
		User user2 = new User();
		user2.setUserId("2");
		user2.setCreatorId("2");
		
		List<User> userList = Lists.newArrayList(user1, user2);
		
		UserIdToUserNameVoMultiFieldTranslator t = new UserIdToUserNameVoMultiFieldTranslator();
		t.translate(userList);
		
		logger.info("result -> {}", userList);
	}

}
