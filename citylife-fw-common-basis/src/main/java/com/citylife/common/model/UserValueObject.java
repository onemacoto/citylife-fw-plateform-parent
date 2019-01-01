package com.citylife.common.model;

import java.io.Serializable;

public class UserValueObject implements IHeaderUser, Serializable {

  private static final long serialVersionUID = 1L;

  private Long userId;

  @Override
  public Long getUserId() {
    return userId;
  }

  @Override
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  
  public static IHeaderUser empty() {
    return new UserValueObject();
  }
}
