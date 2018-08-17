package com.nicchagil.util.index.tokenizer;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EveryWordTokenizer implements Tokenizer {
	
	private static Logger logger = LoggerFactory.getLogger(EveryWordTokenizer.class);

	@Override
	public List<String> participle(String s) {
		if (StringUtils.isBlank(s)) {
			return null;
		}
		
		List<String> list = Lists.newLinkedList();
		
		char[] chars = s.toCharArray();
		// 开始索引
		for (int startIndex = 0; startIndex < chars.length; startIndex++) {
			// 字长
			for (int wordLength = 1; wordLength <= chars.length - startIndex; wordLength++) {
				// 结束索引
				int endIndex = startIndex + wordLength;
				logger.debug("startIndex : {}, wordLength : {}, endIndex : {}", startIndex, wordLength, endIndex);
				list.add(s.substring(startIndex, endIndex));
			}
		}
		
		return list;
	}
	
	@Test
	public void substringTest() {
		String s = "hello";
		for (int i = 0; i <= s.length(); i++) {
			logger.info("substring({}, {}) : {}", 0, i, s.substring(0, i));
		}
	}
	
	@Test
	public void participleTest() {
		String s = "hello";
		logger.info("result : {}", new EveryWordTokenizer().participle(s));
	}

}
