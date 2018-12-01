package com.citylife.function.core.boot.template.context;

import java.util.Map;

public class TemplateActionContextFactory<C extends TemplateActionContext> {
  Class<C> theClass = null;

  public TemplateActionContextFactory(Class<C> theClass) {
    this.theClass = theClass;
  }

  public C createInstance(Map<String, ?> parameter) {
    try {
      C instance = this.theClass.newInstance();
      instance.setParameter(parameter);
      return instance;
    } catch (Exception e) {
    }
    return null;
  }
}
