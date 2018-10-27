package com.nicchagil.orm.mapper;

import com.nicchagil.orm.entity.EcSeckill;
import com.nicchagil.orm.entity.EcSeckillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcSeckillMapper {
    int countByExample(EcSeckillExample example);

    int deleteByExample(EcSeckillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcSeckill record);

    int insertSelective(EcSeckill record);

    List<EcSeckill> selectByExample(EcSeckillExample example);

    EcSeckill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcSeckill record, @Param("example") EcSeckillExample example);

    int updateByExample(@Param("record") EcSeckill record, @Param("example") EcSeckillExample example);

    int updateByPrimaryKeySelective(EcSeckill record);

    int updateByPrimaryKey(EcSeckill record);
}