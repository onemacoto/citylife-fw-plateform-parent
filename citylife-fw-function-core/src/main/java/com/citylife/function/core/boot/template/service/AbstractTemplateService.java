package com.citylife.function.core.boot.template.service;

import java.util.Map;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.citylife.function.core.boot.template.action.ITemplateAciton;
import com.citylife.function.core.boot.template.bean.FunctionResult;
import com.citylife.function.core.boot.template.context.TemplateActionContext;

public class AbstractTemplateService {
  
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public <C extends TemplateActionContext> FunctionResult<?> excute(ITemplateAciton<C> action, Map<String, ?> parameter) {
    return excuteWithoutTransaction(action, parameter);
  }

  public <C extends TemplateActionContext> FunctionResult<?> excuteWithoutTransaction(ITemplateAciton<C> action, Map<String, ?> parameter) {
    String actionName = action.getActionName();
    try {
      C context = action.createContext(parameter);
      FunctionResult<?> result = action.validate(context);
      if (result.hasError()) {
        return result;
      }
      return action.execute(context);
    } finally {
      
    }
  }

}
