package com.nicchagil.util.desctranslator;

import java.util.Map;

import com.google.common.collect.Maps;

public class TranslatorSetVo<T> {

	private Map<String, AbstractVoSingleFieldTranslator<T>> fieldNameTranslatorMap = Maps.newHashMap();
	
	public TranslatorSetVo<T> add(String fieldName, AbstractVoSingleFieldTranslator<T> translator) {
		fieldNameTranslatorMap.put(fieldName, translator);
		return this;
	}

	public Map<String, AbstractVoSingleFieldTranslator<T>> getFieldNameTranslatorMap() {
		return fieldNameTranslatorMap;
	}

	public void setFieldNameTranslatorMap(Map<String, AbstractVoSingleFieldTranslator<T>> fieldNameTranslatorMap) {
		this.fieldNameTranslatorMap = fieldNameTranslatorMap;
	}

}
