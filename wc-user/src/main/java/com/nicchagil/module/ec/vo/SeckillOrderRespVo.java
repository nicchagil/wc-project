package com.nicchagil.module.ec.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SeckillOrderRespVo {

	private boolean enough = false;

	public SeckillOrderRespVo(boolean enough) {
		super();
		this.enough = enough;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
