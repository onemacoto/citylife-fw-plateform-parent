package com.citylife.function.core.boot.template.context;

import com.citylife.common.model.IHeaderUser;

public interface IActionContext<T> {
  T getParameter();
  void setParameter(T parameter);
  IHeaderUser getUser();
  void setUser(IHeaderUser uvo);
  String getToken();
  void setToken(String token);
  String getVersion();
  void setVersion(String version);
}
