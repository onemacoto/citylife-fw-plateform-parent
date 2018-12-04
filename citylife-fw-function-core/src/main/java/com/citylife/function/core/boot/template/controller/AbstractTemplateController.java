package com.citylife.function.core.boot.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import com.citylife.function.core.annotations.ActionTransactional;
import com.citylife.function.core.boot.template.action.IAciton;
import com.citylife.function.core.boot.template.bean.FunctionResult;
import com.citylife.function.core.boot.template.context.TemplateActionContext;
import com.citylife.function.core.boot.template.service.TemplateService;
import com.citylife.function.core.utils.AnnotationUtils;

public class AbstractTemplateController<S extends TemplateService> {

  private S service;

  @Autowired
  public void setService(S service) {
    this.service = service;
  }

  protected <T, C extends TemplateActionContext<T>> FunctionResult<?> doAction(IAciton<T, C> action,
      T parameter, BindingResult bindingResult) {

    FunctionResult<?> result = null;
    if (bindingResult.hasErrors()) {
      return FunctionResult.failure(FunctionResult.BINDING_ERROR);
    }

    try {
      if (AnnotationUtils.isAnnotated(action, ActionTransactional.class)) {
        result = service.excute(action, parameter);
      } else {
        result = service.excuteWithoutTransaction(action, parameter);
      }
    } catch (Throwable t) {
      return FunctionResult.failure(FunctionResult.SYSTEM_ERROR);
    }
    return result;
  }

}
