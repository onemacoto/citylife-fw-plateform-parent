package com.citylife.function.core.boot.template.context;

import com.citylife.common.model.IUser;

public class TemplateActionContext<T> implements IActionContext<T> {

  private T parameter;
  
  private IUser uvo;

  public T getParameter() {
    return parameter;
  }

  public void setParameter(T parameter) {
    this.parameter = parameter;
  }

  @Override
  public IUser getUser() {
    return this.uvo;
  }

  @Override
  public void setUser(IUser uvo) {
    this.uvo = uvo;
  }

}
