package com.citylife.function.core.boot.template;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.citylife.common.model.ResultEntity;
import com.citylife.function.core.boot.template.context.IActionContext;

public class TemplateService {

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public <P, R> ResultEntity<R> excute(final String version, final ITemplateAciton<P, R> action, final P parameter, final String token) {
    return excuteWithoutTransaction(version, action, parameter, token);
  }

  public <P, R> ResultEntity<R> excuteWithoutTransaction(final String version, final ITemplateAciton<P, R> action, final P parameter,
      final String token) {
    IActionContext<P> context = action.createContext(version, parameter, token);
    ResultEntity<R> result = action.validate(context);
    if (result.hasError()) {
      return result;
    }
    return action.execute(context);
  }

}
