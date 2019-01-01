package com.citylife.function.core.method.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.citylife.common.model.IHeaderUser;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {
  
  @Autowired
  private IUserBuilder userBuilder;

  public UserArgumentResolver() {
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return IHeaderUser.class.isAssignableFrom(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    IHeaderUser user = userBuilder.build(parameter, mavContainer, webRequest, binderFactory);
    return user;
  }

} 
