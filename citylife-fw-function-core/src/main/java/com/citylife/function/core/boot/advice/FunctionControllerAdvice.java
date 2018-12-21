package com.citylife.function.core.boot.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.citylife.common.component.TraceHelper;
import com.citylife.common.constants.SystemMessageConsts;
import com.citylife.common.logging.AdminLogger;
import com.citylife.common.message.MessageResolver;
import com.citylife.common.model.ResultEntity;

@ControllerAdvice
public class FunctionControllerAdvice {
  @Autowired
  private MessageResolver messageResolver;
  
  @Autowired
  private TraceHelper traceHelper;

  @ExceptionHandler(value = Throwable.class)
  @ResponseBody
  public ResultEntity<?> errorHandler(HttpServletRequest httpRequest, Throwable t) {
    AdminLogger.error("系统异常发生", t);
    ResultEntity<?> result = ResultEntity.failure(ResultEntity.SYSTEM_ERROR, messageResolver.error(SystemMessageConsts.SYSTEM_ERROR_UNEXPECTED));
    result.setTraceId(traceHelper.getTraceId(httpRequest));
    return result;
  }
}
