package com.nicchagil.util.desctranslator;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public abstract class AbstractVoListMultiFieldTranslator<V> implements IVoListMultiFieldTranslator<V> {

	@Override
	public List<V> translate(List<V> voList) {
		Map<String, AbstractVoSingleFieldTranslator> fieldNameTranslatorMap = Maps.newHashMap();
		return null;
	}

}
