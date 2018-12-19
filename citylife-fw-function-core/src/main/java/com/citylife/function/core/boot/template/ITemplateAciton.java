package com.citylife.function.core.boot.template;

import com.citylife.common.model.ResultEntity;
import com.citylife.function.core.boot.template.context.IActionContext;

public interface ITemplateAciton<P, R> extends IAction {

  IActionContext<P> createContext(P parameter, String token);

  default ResultEntity<R> validate(IActionContext<P> context) {
    return ResultEntity.ok();
  };

  ResultEntity<R> execute(IActionContext<P> context);

}
