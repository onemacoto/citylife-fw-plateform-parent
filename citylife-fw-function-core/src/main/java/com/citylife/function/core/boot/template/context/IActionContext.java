package com.citylife.function.core.boot.template.context;

import com.citylife.common.model.IUser;

public interface IActionContext<T> {
  T getParameter();
  void setParameter(T parameter);
  IUser getUser();
  void setUser(IUser uvo);
}
