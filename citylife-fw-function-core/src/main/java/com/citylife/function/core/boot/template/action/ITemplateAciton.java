package com.citylife.function.core.boot.template.action;

import java.util.Map;

import com.citylife.function.core.boot.template.bean.FunctionResult;
import com.citylife.function.core.boot.template.context.TemplateActionContext;

public interface ITemplateAciton<C extends TemplateActionContext> {

  String getActionName();
  C createContext(Map<String, ?> parameter);
  FunctionResult<?> validate(C context);
  FunctionResult<?> execute(C context);

}
