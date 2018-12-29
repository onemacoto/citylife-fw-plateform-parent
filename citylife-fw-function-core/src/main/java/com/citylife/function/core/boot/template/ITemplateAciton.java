package com.citylife.function.core.boot.template;

import com.citylife.common.model.RequestVO;
import com.citylife.common.model.ResponseVO;
import com.citylife.common.model.ResultEntity;
import com.citylife.function.core.boot.template.context.IActionContext;

public interface ITemplateAciton<P extends RequestVO<?>, R extends ResponseVO<?>> extends IAction {

  IActionContext<P> createContext(String version, P parameter, String token);

  default ResultEntity<R> validate(IActionContext<P> context) {
    return ResultEntity.ok();
  };

  ResultEntity<R> execute(IActionContext<P> context);
}
