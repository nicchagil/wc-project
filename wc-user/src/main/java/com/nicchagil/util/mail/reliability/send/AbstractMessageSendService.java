package com.nicchagil.util.mail.reliability.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nicchagil.orm.entity.MessageSendLog;
import com.nicchagil.util.mail.reliability.MessageSendLogOpsService;

@Service
public abstract class AbstractMessageSendService {
	
	@Autowired
	private MessageSendLogOpsService messageSendLogService;
	
	/**
	 * 实际的发送方法
	 */
	public abstract void doSend(MessageSendLog record) throws RuntimeException;

	/**
	 * 发送邮件
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void send(MessageSendLog record) {
		Exception exception = null;
		
		try {
			/* 递增尝试次数（新事务） */
			MessageSendLog increaseNumRecord = new MessageSendLog();
			increaseNumRecord.setId(record.getId());
			increaseNumRecord.setTries(3);
			int increaseNum = this.messageSendLogService.increaseTriesInNewTransaction(increaseNumRecord);
			Assert.isTrue(increaseNum == 1, "递增尝试次数异常：" + increaseNum);
			
			/* 更新状态为成功（当前事务中） */
			MessageSendLog updateStatusNumRecord = new MessageSendLog();
			updateStatusNumRecord.setId(record.getId());
			updateStatusNumRecord.setTries(3);
			updateStatusNumRecord.setStatus("N");
			int updateStatusNum = this.messageSendLogService.updateStatusInRequiredTransaction(updateStatusNumRecord);
			Assert.isTrue(increaseNum == 1, "更新状态为成功异常：" + updateStatusNum);
			
			this.doSend(record);
		} catch (Exception e) {
			
			/* 递增尝试次数（新事务） */
			MessageSendLog exceptionRecord = new MessageSendLog();
			exceptionRecord.setId(record.getId());
			exceptionRecord.setExceptionMsg(e.getMessage());
			this.messageSendLogService.updateExceptionMsgInNewTransaction(exceptionRecord);
			
			exception = e;
			throw new RuntimeException(exception);
		}
	}

}
