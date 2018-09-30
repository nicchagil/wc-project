package com.nicchagil.util.desctranslator;

public interface IVoSingleFieldTranslator<VO> {

	public VO translateVo(VO vo, String valueFieldName);

}
