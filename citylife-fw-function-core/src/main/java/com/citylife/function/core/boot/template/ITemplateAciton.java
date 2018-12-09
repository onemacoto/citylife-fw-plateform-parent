package com.citylife.function.core.boot.template;

import com.citylife.common.model.IUser;
import com.citylife.function.core.boot.template.bean.ResponseData;
import com.citylife.function.core.boot.template.context.IActionContext;

public interface ITemplateAciton<P, C extends IActionContext<P>> extends IAction {

  C createContext(P parameter, IUser uvo);

  default ResponseData<?> validate(C context) {
    return ResponseData.ok();
  };

  ResponseData<?> execute(C context);

}
