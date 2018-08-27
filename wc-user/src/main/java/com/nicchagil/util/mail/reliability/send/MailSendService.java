package com.nicchagil.util.mail.reliability.send;

import com.nicchagil.orm.entity.MessageSendLog;
import com.nicchagil.util.random.RandomUtils;

public class MailSendService extends AbstractMessageSendService {

	@Override
	public void doSend(MessageSendLog record) throws RuntimeException {
		/* 模拟发送邮件，偶有异常
		 * 注：发送失败，会抛出异常，导致回滚“当前事务中”的数据库操作，但不会回滚“新事务”的数据库操作 */
		if (0 == RandomUtils.getRandomNumber(2)) {
			throw new RuntimeException("模拟异常");
		}
	}

}
