package com.citylife.function.core.boot.template.context;

import org.springframework.util.StringUtils;

import com.citylife.common.model.IUser;
import com.citylife.common.model.UserValueObject;
import com.citylife.common.utils.ExceptionUtils;
import com.citylife.common.utils.JWTUtils;

public class TemplateActionContextFactory<T> {

  public <R extends IActionContext<T>> R createInstance(T parameter, String token,  Class<R> cls) {
    try {
      R instance = cls.newInstance();
      instance.setParameter(parameter);
      IUser uvo = StringUtils.hasText(token) ? JWTUtils.parseToken(token, UserValueObject.class) : UserValueObject.empty();
      instance.setUser(uvo);
      instance.setToken(token);
      return instance;
    } catch (Exception e) {
      ExceptionUtils.wrapAndThrow(e);
    }
    return null;
  }
}
