package com.nicchagil.orm.mapper;

import com.nicchagil.orm.entity.MailLog;

public interface MailLogExtraMapper {
	int increaseTries(MailLog record);
    int updateStatus(MailLog record);
}