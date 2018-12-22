package com.citylife.function.core.api.feign;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;

import com.citylife.common.constants.SystemMessageConsts;
import com.citylife.common.logging.AdminLogger;
import com.citylife.common.message.MessageResolver;
import com.citylife.common.model.ResultEntity;

import feign.hystrix.FallbackFactory;

public abstract class AbstractClientFallbackFactory<T> implements FallbackFactory<T> {

  @Autowired
  private MessageResolver messageResolver;

  @SuppressWarnings("unchecked")
  @Override
  public T create(Throwable cause) {
    AdminLogger.error("feign client fallback error occured.", cause);
    FallbackProxy proxy = new FallbackProxy();
    return (T) Proxy.newProxyInstance(getClass().getClassLoader(), ArrayUtils.toArray(getGenericClass(0)), proxy);
  }

  @SuppressWarnings("unchecked")
  protected <M> Class<M> getGenericClass(int index) {
    try {
      Type genericType = ((ParameterizedType) ClassUtils.getUserClass(this).getGenericSuperclass())
          .getActualTypeArguments()[index];
      if (genericType instanceof ParameterizedType) {
        return (Class<M>) ((ParameterizedType) genericType).getRawType();
      }
      return (Class<M>) genericType;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public class FallbackProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      return ResultEntity.failure(ResultEntity.FALLBACK_ERROR,
          messageResolver.error(SystemMessageConsts.SYSTEM_ERROR_FALLBACK));
    }
  }

}
