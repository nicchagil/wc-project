package com.nicchagil.orm.mapper;

import com.nicchagil.orm.entity.MessageSendLog;
import com.nicchagil.orm.entity.MessageSendLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageSendLogMapper {
    int countByExample(MessageSendLogExample example);

    int deleteByExample(MessageSendLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageSendLog record);

    int insertSelective(MessageSendLog record);

    List<MessageSendLog> selectByExampleWithBLOBs(MessageSendLogExample example);

    List<MessageSendLog> selectByExample(MessageSendLogExample example);

    MessageSendLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageSendLog record, @Param("example") MessageSendLogExample example);

    int updateByExampleWithBLOBs(@Param("record") MessageSendLog record, @Param("example") MessageSendLogExample example);

    int updateByExample(@Param("record") MessageSendLog record, @Param("example") MessageSendLogExample example);

    int updateByPrimaryKeySelective(MessageSendLog record);

    int updateByPrimaryKeyWithBLOBs(MessageSendLog record);

    int updateByPrimaryKey(MessageSendLog record);
}