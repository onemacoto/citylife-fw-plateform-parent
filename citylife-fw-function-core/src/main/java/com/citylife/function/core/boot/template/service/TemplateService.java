package com.citylife.function.core.boot.template.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.citylife.common.model.IUser;
import com.citylife.function.core.boot.template.ITemplateAciton;
import com.citylife.function.core.boot.template.bean.ResponseData;
import com.citylife.function.core.boot.template.context.IActionContext;

public class TemplateService {

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public <T, C extends IActionContext<T>> ResponseData<?> excute(ITemplateAciton<T, C> action, T parameter, IUser uvo) {
    return excuteWithoutTransaction(action, parameter, uvo);
  }

  public <T, C extends IActionContext<T>> ResponseData<?> excuteWithoutTransaction(
      final ITemplateAciton<T, C> action, final T parameter, final IUser uvo) {
      C context = action.createContext(parameter, uvo);
      ResponseData<?> result = action.validate(context);
      if (result.hasError()) {
        return result;
      }
      return action.execute(context);
  }

}
