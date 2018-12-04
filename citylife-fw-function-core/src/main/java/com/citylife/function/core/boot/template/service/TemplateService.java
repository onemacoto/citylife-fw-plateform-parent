package com.citylife.function.core.boot.template.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.citylife.function.core.boot.template.action.IAciton;
import com.citylife.function.core.boot.template.bean.FunctionResult;
import com.citylife.function.core.boot.template.context.IActionContext;

public class TemplateService {

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public <T, C extends IActionContext<T>> FunctionResult<?> excute(IAciton<T, C> action, T parameter) {
    return excuteWithoutTransaction(action, parameter);
  }

  public <T, C extends IActionContext<T>> FunctionResult<?> excuteWithoutTransaction(
      IAciton<T, C> action, T parameter) {
      C context = action.createContext(parameter);
      FunctionResult<?> result = action.validate(context);
      if (result.hasError()) {
        return result;
      }
      return action.execute(context);
  }

}
