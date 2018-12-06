package com.citylife.function.core.boot.template.action;

import com.citylife.function.core.boot.template.bean.FunctionResult;
import com.citylife.function.core.boot.template.context.IActionContext;

public interface ITemplateAciton<P, C extends IActionContext<P>> extends IAction {

  C createContext(P parameter);

  default FunctionResult<?> validate(C context) {
    return FunctionResult.success();
  };

  FunctionResult<?> execute(C context);

}
