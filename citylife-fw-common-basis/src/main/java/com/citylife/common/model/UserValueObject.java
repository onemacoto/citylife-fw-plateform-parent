package com.citylife.common.model;

import java.io.Serializable;

public class UserValueObject implements IUser, Serializable {

  private static final long serialVersionUID = 1L;

  private String userId;

  @Override
  public String getUserId() {
    return userId;
  }

  @Override
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public static IUser empty() {
    return new UserValueObject();
  }
}
