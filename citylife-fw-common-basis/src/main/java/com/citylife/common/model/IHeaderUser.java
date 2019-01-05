package com.citylife.common.model;

public interface IHeaderUser {
	public static final String HEADER_KEY = "CITYLIFE-USER";

	Long getUserId();

	void setUserId(Long userId);

	String getUserName();

	void setUserName(String userName);

}
