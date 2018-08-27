package com.nicchagil.orm.mapper;

import com.nicchagil.orm.entity.MessageSendLog;

public interface MessageSendLogExtraMapper {
	int increaseTries(MessageSendLog record);
    int updateStatus(MessageSendLog record);
}