package com.nicchagil.orm.mapper;

import com.nicchagil.orm.entity.EcOrder;
import com.nicchagil.orm.entity.EcOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcOrderMapper {
    int countByExample(EcOrderExample example);

    int deleteByExample(EcOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcOrder record);

    int insertSelective(EcOrder record);

    List<EcOrder> selectByExample(EcOrderExample example);

    EcOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcOrder record, @Param("example") EcOrderExample example);

    int updateByExample(@Param("record") EcOrder record, @Param("example") EcOrderExample example);

    int updateByPrimaryKeySelective(EcOrder record);

    int updateByPrimaryKey(EcOrder record);
}