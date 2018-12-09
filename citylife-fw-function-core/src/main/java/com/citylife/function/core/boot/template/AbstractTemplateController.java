package com.citylife.function.core.boot.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;

import com.citylife.common.logging.AdminLogger;
import com.citylife.common.model.IUser;
import com.citylife.common.utils.AnnotationUtils;
import com.citylife.function.core.annotations.ActionTransactional;
import com.citylife.function.core.boot.template.bean.ResponseData;
import com.citylife.function.core.boot.template.context.IActionContext;
import com.citylife.function.core.boot.template.service.TemplateService;
import com.citylife.function.core.log.IOperationLogger;

public class AbstractTemplateController<S extends TemplateService> {

  private S service;

  @Autowired
  public void setService(S service) {
    this.service = service;
  }

  @Autowired(required = false)
  IOperationLogger operationLogger;

  protected <T, C extends IActionContext<T>> ResponseData<?> doAction(final ITemplateAciton<T, C> action, final T parameter, final IUser uvo,
      final BindingResult bindingResult) {

    if (operationLogger != null) {
      operationLogger.logOperationBegin(action);
    }

    StopWatch sw = new StopWatch();
    try {
      sw.start();
      ResponseData<?> result = null;
      if (bindingResult.hasErrors()) {
        return ResponseData.failure(ResponseData.BINDING_ERROR);
      }
      if (AnnotationUtils.isAnnotated(action, ActionTransactional.class)) {
        result = service.excute(action, parameter, uvo);
      } else {
        result = service.excuteWithoutTransaction(action, parameter, uvo);
      }
      return result;
    } catch (Throwable t) {
      AdminLogger.error(String.format("exception happen when call action [%s]", (action == null)? "undefined action" : action.getActionName()), t);
      return ResponseData.failure(ResponseData.SYSTEM_ERROR);
    } finally {
      sw.stop();
      if (operationLogger != null) {
        operationLogger.logOperationEnd(action, sw.getTotalTimeMillis());
      }
    }

  }

}
