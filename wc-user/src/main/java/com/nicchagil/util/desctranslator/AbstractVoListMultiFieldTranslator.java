package com.nicchagil.util.desctranslator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.shiro.util.CollectionUtils;

public abstract class AbstractVoListMultiFieldTranslator<V> implements IVoListMultiFieldTranslator<V> {

	@Override
	public List<V> translate(List<V> voList) {
		if (CollectionUtils.isEmpty(voList)) {
			return null;
		}
		
		TranslatorSetVo<V> translatorSetVo = this.getTranslatorSetVo();
		Map<String, AbstractVoSingleFieldTranslator<V>> fieldNameTranslatorMap = translatorSetVo.getFieldNameTranslatorMap();
		
		if (fieldNameTranslatorMap == null || fieldNameTranslatorMap.size() == 0) {
			return voList;
		}
		
		for (V vo : voList) {
			Set<Entry<String, AbstractVoSingleFieldTranslator<V>>> entrySet = fieldNameTranslatorMap.entrySet();
			for (Entry<String, AbstractVoSingleFieldTranslator<V>> entry : entrySet) {
				String key = entry.getKey();
				AbstractVoSingleFieldTranslator<V> abstractVoSingleFieldTranslator = entry.getValue();
				
				abstractVoSingleFieldTranslator.translateVo(vo, key);
			}
		}
		
		return voList;
	}
	
	public abstract TranslatorSetVo<V> getTranslatorSetVo();

}
