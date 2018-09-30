package com.nicchagil.util.desctranslator;

import java.util.List;

public interface IVoListSingleFieldTranslator<VO> {

	public List<VO> translateVoList(List<VO> voList, String valueFieldName);

}
