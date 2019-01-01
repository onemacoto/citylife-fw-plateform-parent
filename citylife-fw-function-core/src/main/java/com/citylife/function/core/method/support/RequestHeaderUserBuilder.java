package com.citylife.function.core.method.support;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSONObject;
import com.citylife.common.model.IHeaderUser;
import com.citylife.common.model.UserValueObject;

public class RequestHeaderUserBuilder implements IUserBuilder {

  @Override
  public IHeaderUser build(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) {
    String uvoJson = webRequest.getHeader(IHeaderUser.HEADER_KEY);
    return StringUtils.hasText(uvoJson) ? JSONObject.parseObject(uvoJson, UserValueObject.class) : UserValueObject.empty();
  }

}
