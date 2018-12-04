package com.citylife.function.core.boot.template.action;

import com.citylife.function.core.boot.template.bean.FunctionResult;
import com.citylife.function.core.boot.template.context.IActionContext;

public interface IAciton<P, C extends IActionContext<P>> {

  String getActionName();

  C createContext(P parameter);

  default FunctionResult<?> validate(C context) {
    return FunctionResult.success();
  };

  FunctionResult<?> execute(C context);

}
