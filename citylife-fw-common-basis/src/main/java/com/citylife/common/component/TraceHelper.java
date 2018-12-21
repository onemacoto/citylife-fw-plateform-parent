package com.citylife.common.component;


import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import brave.propagation.TraceContext;

public class TraceHelper {
  
  public Optional<TraceContext> getTraceContext(HttpServletRequest httpRequest) {
    TraceContext context = (TraceContext) httpRequest.getAttribute(TraceContext.class.getName());
    return Optional.ofNullable(context);
  }
  
  public String getTraceId(HttpServletRequest httpRequest) {
    Optional<TraceContext> context = getTraceContext(httpRequest);
    if (!context.isPresent()) {
      return StringUtils.EMPTY;
    }
    return context.get().traceIdString();
  }
}
