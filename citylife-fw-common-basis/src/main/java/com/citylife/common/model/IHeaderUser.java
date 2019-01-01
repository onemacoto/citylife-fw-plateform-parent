package com.citylife.common.model;

import com.github.dozermapper.core.Mapping;

public interface IHeaderUser {
  public static final String HEADER_KEY = "CITYLIFE-USER";
  @Mapping("userId")
  Long getUserId();
  void setUserId(Long userId);
  
}
