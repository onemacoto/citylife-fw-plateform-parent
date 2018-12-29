package com.citylife.function.core.boot.template;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.citylife.common.model.RequestVO;
import com.citylife.common.model.ResponseVO;
import com.citylife.common.model.ResultEntity;
import com.citylife.function.core.boot.template.context.IActionContext;

public class TemplateService {

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public <P extends RequestVO<?>, R extends ResponseVO<?>> ResultEntity<R> excute(final ITemplateAciton<P, R> action, final P parameter, final String version, final String token) {
    return excuteWithoutTransaction(action, parameter, version, token);
  }

  public <P extends RequestVO<?>, R extends ResponseVO<?>> ResultEntity<R> excuteWithoutTransaction(final ITemplateAciton<P, R> action, final P parameter,
		  final String version, final String token) {
    IActionContext<P> context = action.createContext(version, parameter, token);
    ResultEntity<R> result = action.validate(context);
    if (result.hasError()) {
      return result;
    }
    return action.execute(context);
  }

}
