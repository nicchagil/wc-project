package com.nicchagil.orm.mapper;

import com.nicchagil.orm.entity.MailLog;
import com.nicchagil.orm.entity.MailLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MailLogMapper {
    int countByExample(MailLogExample example);

    int deleteByExample(MailLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MailLog record);

    int insertSelective(MailLog record);

    List<MailLog> selectByExampleWithBLOBs(MailLogExample example);

    List<MailLog> selectByExample(MailLogExample example);

    MailLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MailLog record, @Param("example") MailLogExample example);

    int updateByExampleWithBLOBs(@Param("record") MailLog record, @Param("example") MailLogExample example);

    int updateByExample(@Param("record") MailLog record, @Param("example") MailLogExample example);

    int updateByPrimaryKeySelective(MailLog record);

    int updateByPrimaryKeyWithBLOBs(MailLog record);

    int updateByPrimaryKey(MailLog record);
}