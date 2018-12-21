package com.citylife.function.core.method.support;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.citylife.common.component.TraceHelper;
import com.citylife.common.model.ResultEntity;

public class ResultEntityHandlerMethodReturnValueHandler extends RequestResponseBodyMethodProcessor {

  public ResultEntityHandlerMethodReturnValueHandler(List<HttpMessageConverter<?>> converters,
      @Nullable ContentNegotiationManager manager, @Nullable List<Object> requestResponseBodyAdvice) {
    super(converters, manager, requestResponseBodyAdvice);
    // TODO Auto-generated constructor stub
  }

  @Autowired
  private TraceHelper traceHelper;

  @Override
  public boolean supportsReturnType(MethodParameter returnType) {
    return super.supportsReturnType(returnType) && ResultEntity.class.isAssignableFrom(returnType.getParameterType());
  }

  @Override
  public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
      ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
      throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {

    if (returnValue != null && ResultEntity.class.isAssignableFrom(returnType.getParameterType())) {
      ((ResultEntity<?>) returnValue)
          .setTraceId(traceHelper.getTraceId(webRequest.getNativeRequest(HttpServletRequest.class)));
    }
    super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);

  }
}
