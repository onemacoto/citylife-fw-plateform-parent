package com.citylife.function.core.boot.template.context;

public interface IActionContext<T> {
  T getParameter();
  void setParameter(T parameter);
}
