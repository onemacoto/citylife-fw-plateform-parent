package com.citylife.function.core.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.citylife.function.core.method.support.ResultEntityHandlerMethodReturnValueHandler;

@Configuration
@ConditionalOnBean(RequestMappingHandlerAdapter.class)
public class FunctionResolverAdapterConfig {
  
  @Autowired
  @Qualifier("requestMappingHandlerAdapter")
  private RequestMappingHandlerAdapter adapter;
  
  @Autowired
  private ResultEntityHandlerMethodReturnValueHandler resultEntityHandlerMethodReturnValueHandler;
  
  
  @PostConstruct
  public void injectSelfMethodArgumentResolver() {
    List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<>();
    returnValueHandlers.add(resultEntityHandlerMethodReturnValueHandler);
    returnValueHandlers.addAll(adapter.getReturnValueHandlers());
    adapter.setReturnValueHandlers(returnValueHandlers);
  }

}
