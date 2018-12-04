package com.citylife.function.core.boot.template.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.util.ClassUtils;

import com.citylife.function.core.boot.template.bean.FunctionResult;
import com.citylife.function.core.boot.template.context.IActionContext;
import com.citylife.function.core.boot.template.context.TemplateActionContextFactory;
import com.citylife.function.core.exception.MethodNotSupportedException;

public abstract class AbstractTemplateAction<P, C extends IActionContext<P>> implements IAciton<P, C> {

  protected Class<C> getContextClass() {
    return getGenericClass(1);
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
    }
    return null;
  }

  @Override
  public String getActionName() {
    Class<?> clazz = ClassUtils.getUserClass(this.getClass());
    return ClassUtils.getShortName(clazz);
  }

  @Override
  public C createContext(P parameter) {
    return new TemplateActionContextFactory<P>().createInstance(parameter, getContextClass());
  }

  @Override
  public FunctionResult<?> execute(C context) {
    throw new MethodNotSupportedException("the method exucete is not supported");
  }

}
