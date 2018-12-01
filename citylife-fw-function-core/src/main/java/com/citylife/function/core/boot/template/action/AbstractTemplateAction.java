package com.citylife.function.core.boot.template.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.util.ClassUtils;

import com.citylife.function.core.boot.template.bean.FunctionResult;
import com.citylife.function.core.boot.template.context.TemplateActionContext;
import com.citylife.function.core.boot.template.context.TemplateActionContextFactory;

public abstract class AbstractTemplateAction<C extends TemplateActionContext> implements ITemplateAciton<C> {

  protected Class<C> getContextClass() {
    return getGenericClass(0);
  }
  
  @SuppressWarnings("unchecked")
  protected <M> Class<M> getGenericClass(int index) {
      try {
          
          Type genericType = ((ParameterizedType) ClassUtils.getUserClass(this)
                  .getGenericSuperclass()).getActualTypeArguments()[index];
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
  public C createContext(Map<String, ?> parameter) {
    return new TemplateActionContextFactory<>(getContextClass()).createInstance(parameter);
  }

  @Override
  public FunctionResult<?> validate(C context) {
    return null;
  }

  @Override
  public FunctionResult<?> execute(C context) {
    return null;
  }

}
