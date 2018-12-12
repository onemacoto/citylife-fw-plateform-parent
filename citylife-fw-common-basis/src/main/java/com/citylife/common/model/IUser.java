package com.citylife.common.model;

import com.github.dozermapper.core.Mapping;

public interface IUser {
  public static final String HEADER_KEY = "CITYLIFE-USER";
  @Mapping("userId")
  String getUserId();
  void setUserId(String userId);
  
}
