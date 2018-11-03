package com.nicchagil.orm.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.nicchagil.module.ec.vo.OrderDisplayVo;
import com.nicchagil.orm.mapper.EcOrderMapper;

public interface EcOrderExtMapper extends EcOrderMapper {
    
	@Select("select o.id, o.user_id as userId, g.name as goodsName, o.num, DATE_FORMAT(o.create_time, '%Y-%m-%d %H:%i:%s') as orderTime from ec_order o inner join ec_goods g on o.goods_id = g.id order by o.id desc")
	List<OrderDisplayVo> getList();
	
}