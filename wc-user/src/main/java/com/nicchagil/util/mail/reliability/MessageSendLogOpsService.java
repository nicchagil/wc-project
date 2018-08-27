package com.nicchagil.util.mail.reliability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nicchagil.orm.entity.MessageSendLog;
import com.nicchagil.orm.mapper.MessageSendLogExtraMapper;
import com.nicchagil.orm.mapper.MessageSendLogMapper;
import com.nicchagil.util.mail.reliability.send.AbstractMessageSendService;

@Service
public class MessageSendLogOpsService {
	
	@Autowired
	private MessageSendLogMapper messageSendLogMapper;
	
	@Autowired
	private MessageSendLogExtraMapper messageSendLogExtraMapper;
	
	@Autowired
	private AbstractMessageSendService mailSendService;
	
	/**
	 * 新增
	 */
	public int insert(MessageSendLog record) {
		return this.messageSendLogMapper.insert(record);
	}
	
	/**
	 * 递增尝试次数（新事务）
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int increaseTriesInNewTransaction(MessageSendLog record) {
		return this.messageSendLogExtraMapper.increaseTries(record);
	}

	/**
	 * 更新状态为成功（当前事务中）
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatusInRequiredTransaction(MessageSendLog record) {
		return this.messageSendLogExtraMapper.updateStatus(record);
	}
	
	/**
	 * 更新异常（新事务）
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int updateExceptionMsgInNewTransaction(MessageSendLog record) {
		MessageSendLog mailLog = new MessageSendLog();
		mailLog.setId(record.getId());
		mailLog.setExceptionMsg(record.getExceptionMsg());
		
		return this.messageSendLogMapper.updateByPrimaryKeySelective(record);
	}

}
