package com.nicchagil.test.thirdpartyframework.commonlang;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToStringBuilderTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void reflectionToStringTest() {
		User user = new User(123, "Nick Huang", new Date(), 1);
		
		logger.info("ToStringBuilder.reflectionToString(user) : {}", ToStringBuilder.reflectionToString(user));
		logger.info("ToStringBuilder.reflectionToString(user, ToStringStyle.SHORT_PREFIX_STYLE) : {}", ToStringBuilder.reflectionToString(user, ToStringStyle.SHORT_PREFIX_STYLE));
	}
	
	public class User {
		private Integer id;
		
		private String name;
		
		private Date birthday;
		
		private Integer status;

		public User(Integer id, String name, Date birthday, Integer status) {
			super();
			this.id = id;
			this.name = name;
			this.birthday = birthday;
			this.status = status;
		}

		public User() {
			super();
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
	}

}
