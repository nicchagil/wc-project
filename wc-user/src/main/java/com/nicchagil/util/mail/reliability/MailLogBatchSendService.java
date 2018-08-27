package com.nicchagil.util.mail.reliability;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nicchagil.orm.entity.MailLog;
import com.nicchagil.orm.entity.MailLogExample;
import com.nicchagil.orm.mapper.MailLogExtraMapper;
import com.nicchagil.orm.mapper.MailLogMapper;
import com.nicchagil.util.mail.reliability.send.AbstractMailSendService;

@Service
public class MailLogBatchSendService {
	
	@Autowired
	private MailLogMapper mailLogMapper;
	
	@Autowired
	private MailLogExtraMapper mailLogExtraMapper;
	
	@Autowired
	private AbstractMailSendService mailSendService;
	
	/**
	 * 新增
	 */
	public int insert(MailLog record) {
		return this.mailLogMapper.insert(record);
	}
	
	/**
	 * 发送需要发送的此批次邮件
	 */
	public void sendRemainMail() {
		List<MailLog> list = this.getSendMailList();
		
		for (MailLog mailLog : list) {
			this.mailSendService.send(mailLog);
		}
	}
	
	/**
	 * 查询需要发送的此批次邮件列表
	 */
	public List<MailLog> getSendMailList() {
		MailLogExample e = new MailLogExample();
		e.createCriteria().andStatusEqualTo("N").andTriesLessThan(3);
		e.setOrderByClause("id desc");
		
		List<MailLog> list = this.mailLogMapper.selectByExample(e);
		return list;
	}
	
	/**
	 * 递增尝试次数（新事务）
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int increaseTriesInNewTransaction(MailLog record) {
		return this.mailLogExtraMapper.increaseTries(record);
	}

	/**
	 * 更新状态为成功（当前事务中）
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateStatusInRequiredTransaction(MailLog record) {
		return this.mailLogExtraMapper.updateStatus(record);
	}
	
	/**
	 * 更新异常（新事务）
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int updateExceptionMsgInNewTransaction(MailLog record) {
		MailLog mailLog = new MailLog();
		mailLog.setId(record.getId());
		mailLog.setExceptionMsg(record.getExceptionMsg());
		
		return this.mailLogMapper.updateByPrimaryKeySelective(record);
	}

}
