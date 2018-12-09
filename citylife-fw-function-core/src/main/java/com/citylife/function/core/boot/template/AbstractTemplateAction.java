package com.citylife.function.core.boot.template;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.util.ClassUtils;

import com.citylife.common.exception.MethodNotSupportedException;
import com.citylife.common.model.IUser;
import com.citylife.common.utils.ExceptionUtils;
import com.citylife.function.core.boot.template.bean.ResponseData;
import com.citylife.function.core.boot.template.context.IActionContext;
import com.citylife.function.core.boot.template.context.TemplateActionContextFactory;

public abstract class AbstractTemplateAction<P, C extends IActionContext<P>> implements ITemplateAciton<P, C> {

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
      ExceptionUtils.wrapAndThrow(e);
    }
    return null;
  }

  @Override
  public String getActionName() {
    Class<?> clazz = ClassUtils.getUserClass(this.getClass());
    return ClassUtils.getShortName(clazz);
  }

  @Override
  public C createContext(final P parameter, final IUser uvo) {
    return new TemplateActionContextFactory<P>().createInstance(parameter, uvo, getContextClass());
  }

  @Override
  public ResponseData<?> execute(C context) {
    throw new MethodNotSupportedException("the method exucete is not supported");
  }

}
