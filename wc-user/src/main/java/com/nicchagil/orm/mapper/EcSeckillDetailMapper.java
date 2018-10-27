package com.nicchagil.orm.mapper;

import com.nicchagil.orm.entity.EcSeckillDetail;
import com.nicchagil.orm.entity.EcSeckillDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcSeckillDetailMapper {
    int countByExample(EcSeckillDetailExample example);

    int deleteByExample(EcSeckillDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcSeckillDetail record);

    int insertSelective(EcSeckillDetail record);

    List<EcSeckillDetail> selectByExample(EcSeckillDetailExample example);

    EcSeckillDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcSeckillDetail record, @Param("example") EcSeckillDetailExample example);

    int updateByExample(@Param("record") EcSeckillDetail record, @Param("example") EcSeckillDetailExample example);

    int updateByPrimaryKeySelective(EcSeckillDetail record);

    int updateByPrimaryKey(EcSeckillDetail record);
}