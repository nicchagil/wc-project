package com.nicchagil.orm.mapper;

import com.nicchagil.orm.entity.EcGoods;
import com.nicchagil.orm.entity.EcGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcGoodsMapper {
    int countByExample(EcGoodsExample example);

    int deleteByExample(EcGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcGoods record);

    int insertSelective(EcGoods record);

    List<EcGoods> selectByExampleWithBLOBs(EcGoodsExample example);

    List<EcGoods> selectByExample(EcGoodsExample example);

    EcGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcGoods record, @Param("example") EcGoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") EcGoods record, @Param("example") EcGoodsExample example);

    int updateByExample(@Param("record") EcGoods record, @Param("example") EcGoodsExample example);

    int updateByPrimaryKeySelective(EcGoods record);

    int updateByPrimaryKeyWithBLOBs(EcGoods record);

    int updateByPrimaryKey(EcGoods record);
}