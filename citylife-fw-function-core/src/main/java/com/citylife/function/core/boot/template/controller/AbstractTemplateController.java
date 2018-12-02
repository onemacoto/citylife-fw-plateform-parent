package com.citylife.function.core.boot.template.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import com.citylife.function.core.boot.template.action.ITemplateAciton;
import com.citylife.function.core.boot.template.bean.FunctionResult;
import com.citylife.function.core.boot.template.context.TemplateActionContext;
import com.citylife.function.core.boot.template.service.AbstractTemplateService;

public class AbstractTemplateController<S extends AbstractTemplateService> {

  private S service;

  @Autowired
  public void setService(S service) {
    this.service = service;
  }

  FunctionResult<?> doAction(ITemplateAciton<? extends TemplateActionContext> action, Map<String, Object> parameter,
      BindingResult bindingResult) {

    FunctionResult<?> result = null;
    if (bindingResult.hasErrors()) {
      return FunctionResult.failure(FunctionResult.BINDING_ERROR);
    }

    try {
      result = service.excute(action, parameter);
    } catch (Throwable t) {
      return FunctionResult.failure(FunctionResult.SYSTEM_ERROR);
    }

    return result;
  }

  FunctionResult<?> doActionWithoutTransction(ITemplateAciton<? extends TemplateActionContext> action,
      Map<String, Object> parameter, BindingResult bindingResult) {
    FunctionResult<?> result = null;
    if (bindingResult.hasErrors()) {
      return FunctionResult.failure(FunctionResult.BINDING_ERROR);
    }
    try {
      result = service.excuteWithoutTransaction(action, parameter);
    } catch (Throwable t) {
      return FunctionResult.failure(FunctionResult.SYSTEM_ERROR);
    }
    return result;
  }
}
