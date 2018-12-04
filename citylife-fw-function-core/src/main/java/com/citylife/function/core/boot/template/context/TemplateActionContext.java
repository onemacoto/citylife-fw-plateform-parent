package com.citylife.function.core.boot.template.context;

public class TemplateActionContext<T> implements IActionContext<T> {

  private T parameter;

  public T getParameter() {
    return parameter;
  }

  public void setParameter(T parameter) {
    this.parameter = parameter;
  }

}
