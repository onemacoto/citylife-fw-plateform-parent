package com.citylife.function.core.boot.template;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.citylife.common.model.IUser;
import com.citylife.function.core.boot.template.bean.ResultEntity;
import com.citylife.function.core.boot.template.context.IActionContext;

public class TemplateService {

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public <P, R> ResultEntity<R> excute(ITemplateAciton<P, R> action, P parameter, IUser uvo) {
    return excuteWithoutTransaction(action, parameter, uvo);
  }

  public <P, R> ResultEntity<R> excuteWithoutTransaction(
      final ITemplateAciton<P, R> action, final P parameter, final IUser uvo) {
      IActionContext<P> context = action.createContext(parameter, uvo);
      ResultEntity<R> result = action.validate(context);
      if (result.hasError()) {
        return result;
      }
      return action.execute(context);
  }

}
