package com.citylife.function.core.boot.template.context;

import com.citylife.function.core.utils.ExceptionUtils;

public class TemplateActionContextFactory<T> {

  public <R extends IActionContext<T>> R createInstance(T parameter, Class<R> cls) {
    try {
      R instance = cls.newInstance();
      instance.setParameter(parameter);
      return instance;
    } catch (Exception e) {
      ExceptionUtils.wrapAndThrow(e);
    }
    return null;
  }
}
