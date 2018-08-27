package com.nicchagil.util.mail.reliability.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nicchagil.orm.entity.MailLog;
import com.nicchagil.util.mail.reliability.MailLogSendOpsService;

@Service
public abstract class AbstractMailSendService {
	
	@Autowired
	private MailLogSendOpsService mailLogService;
	
	/**
	 * 实际的发送方法
	 */
	public abstract void doSend(MailLog record) throws RuntimeException;

	/**
	 * 发送邮件
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void send(MailLog record) {
		Exception exception = null;
		
		try {
			/* 递增尝试次数（新事务） */
			MailLog increaseNumRecord = new MailLog();
			increaseNumRecord.setId(record.getId());
			increaseNumRecord.setTries(3);
			int increaseNum = this.mailLogService.increaseTriesInNewTransaction(increaseNumRecord);
			Assert.isTrue(increaseNum == 1, "递增尝试次数异常：" + increaseNum);
			
			/* 更新状态为成功（当前事务中） */
			MailLog updateStatusNumRecord = new MailLog();
			updateStatusNumRecord.setId(record.getId());
			updateStatusNumRecord.setTries(3);
			updateStatusNumRecord.setStatus("N");
			int updateStatusNum = this.mailLogService.updateStatusInRequiredTransaction(updateStatusNumRecord);
			Assert.isTrue(increaseNum == 1, "更新状态为成功异常：" + updateStatusNum);
			
			this.doSend(record);
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
