package com.nicchagil.util.mail.reliability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nicchagil.orm.entity.MailLog;
import com.nicchagil.util.random.RandomUtils;

public class AbstractMailSendService {
	
	@Autowired
	private MailLogService mailLogService;

	/**
	 * 发送邮件
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void send(MailLog record) {
		Exception exception = null;
		
		try {
			/* 递增尝试次数（新事务） */
			int increaseNum = this.mailLogService.increaseTriesInNewTransaction(record);
			Assert.isTrue(increaseNum == 1, "递增尝试次数异常：" + increaseNum);
			
			/* 更新状态为成功（当前事务中） */
			int updateStatusNum = this.mailLogService.updateStatusInRequiredTransaction(record);
			Assert.isTrue(increaseNum == 1, "更新状态为成功异常：" + updateStatusNum);
			
			/* 模拟发送邮件，偶有异常
			 * 注：发送失败，会抛出异常，导致回滚“当前事务中”的数据库操作，但不会回滚“新事务”的数据库操作 */
			if (0 == RandomUtils.getRandomNumber(2)) {
				throw new RuntimeException("模拟异常");
			}
		} catch (Exception e) {
			
			/* 递增尝试次数（新事务） */
			MailLog exceptionRecord = new MailLog();
			exceptionRecord.setId(record.getId());
			exceptionRecord.setExceptionMsg(e.getMessage());
			this.mailLogService.updateExceptionMsgInNewTransaction(exceptionRecord);
			
			exception = e;
			throw new RuntimeException(exception);
		}
	}

}
