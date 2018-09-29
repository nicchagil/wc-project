package com.nicchagil.util.desctranslator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.shaded.com.google.common.collect.Lists;

import com.nicchagil.util.reflect.ReflectUtils;

public abstract class AbstractVoSingleFieldTranslator<VO> implements IVoListSingleFieldTranslator<VO>, IVoSingleFieldTranslator<VO>, ISingleFieldTranslator {
	
	@Override
	public String translate(String value) {
		if (value == null) {
			return null;
		}
		
		/* 根据值集合获取值-描述Map */
		Map<String, String> valueDescMap = this.getValueDescMapByValueList(Lists.newArrayList(value.toString()));
		
		if (valueDescMap == null || valueDescMap.size() == 0) {
			return null;
		}
		
		// 获取描述
		String desc = valueDescMap.get(value);
		return desc;
	}

	@Override
	public List<VO> translateVoList(List<VO> voList) {
		if (CollectionUtils.isEmpty(voList)) {
			return null; // 置为NULL
		}
		
		/* 根据VO集合获取值集合 */
		List<String> valueList = this.getValueListByVoList(voList);
		
		if (CollectionUtils.isEmpty(valueList)) {
			return voList; // 没有可以转换的值，返回原集合
		}
		
		/* 根据值集合获取值-描述Map */
		Map<String, String> valueDescMap = this.getValueDescMapByValueList(valueList);
		
		if (valueDescMap == null || valueDescMap.size() == 0) {
			return voList; // 没有可以转换的值，返回原集合
		}
		
		String valueFieldName = this.valueFieldName();
		String descFieldName = new StringBuffer(valueFieldName).append("Desc").toString();
		
		for (VO v : voList) {
			// 获取值
			Object valueObject = ReflectUtils.getFieldValue(v, valueFieldName);
			
			if (valueObject == null) {
				continue;
			}
			
			String value = valueObject.toString();
			
			// 获取描述
			String desc = valueDescMap.get(value);
			
			if (StringUtils.isEmpty(desc)) {
				continue;
			}
			
			// 设置描述
			ReflectUtils.setFieldValue(v, descFieldName, desc);
		}
		
		return voList;
	}
	
	@Override
	public VO translateVo(VO vo) {
		if (vo == null) {
			return null;
		}
		
		List<VO> voList = new ArrayList<>();
		voList.add(vo);
		
		this.translateVoList(voList);
		
		return vo;
	}

	/**
	 * 根据VO集合获取值集合
	 */
	public List<String> getValueListByVoList(List<VO> voList) {
		if (CollectionUtils.isEmpty(voList)) {
			return null;
		}
		
		String valueFieldName = this.valueFieldName();
		
		List<String> valueList = Lists.newLinkedList();
		for (VO vo : voList) {
			// 获取值
			Object valueObject = ReflectUtils.getFieldValue(vo, valueFieldName);
			
			if (valueObject == null) {
				continue;
			}
			
			valueList.add(valueObject.toString());
		}
		
		return valueList;
	}
	
	/**
	 * 根据值集合获取值-描述Map
	 */
	public abstract Map<String, String> getValueDescMapByValueList(List<String> valueList);
	
	/**
	 * 需转换的值的属性名
	 */
	public abstract String valueFieldName();

}
