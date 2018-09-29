package com.nicchagil.util.desctranslator;

import java.util.List;

public interface IVoListMultiFieldTranslator<VO> {

	public List<VO> translate(List<VO> voList);

}
