package com.citylife.function.core.method.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.citylife.common.model.IHeaderUser;
import com.citylife.common.model.UserValueObject;
import com.citylife.function.core.properties.FunctionProperties;

public class PropertiesUserBuilder implements IUserBuilder {

  private FunctionProperties properties;

  public PropertiesUserBuilder(@Autowired FunctionProperties properties) {
    this.properties = properties;
  }

  @Override
  public IHeaderUser build(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) {
    UserValueObject user = new UserValueObject();
    user.setUserId(Long.parseLong(properties.getMockUser().getUserId()));
    return user;
  }

}
