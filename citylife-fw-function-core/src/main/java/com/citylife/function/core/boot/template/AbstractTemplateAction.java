package com.citylife.function.core.boot.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;

import com.citylife.common.component.BeanMapper;
import com.citylife.common.exception.MethodNotSupportedException;
import com.citylife.function.core.boot.template.bean.ResultEntity;
import com.citylife.function.core.boot.template.context.IActionContext;
import com.citylife.function.core.boot.template.context.TemplateActionContext;
import com.citylife.function.core.boot.template.context.TemplateActionContextFactory;

public abstract class AbstractTemplateAction<P, R> implements ITemplateAciton<P, R> {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private TemplateActionContextFactory<P> actionContextFactory;

	protected BeanMapper getBeanMapper() {
		return beanMapper;
	}

	protected TemplateActionContextFactory<P> getActionContextFactory() {
		return actionContextFactory;
	}

	@Override
	public String getActionName() {
		Class<?> clazz = ClassUtils.getUserClass(this.getClass());
		return ClassUtils.getShortName(clazz);
	}

	@Override
	public IActionContext<P> createContext(final P parameter, final String token) {
		return getActionContextFactory().createInstance(parameter, token, getContextClass());
	}

	@Override
	public ResultEntity<R> execute(IActionContext<P> context) {
		throw new MethodNotSupportedException("the method exucete is not supported");
	}

	@SuppressWarnings("unchecked")
	public Class<IActionContext<P>> getContextClass() {
		return (Class<IActionContext<P>>) TemplateActionContext.class.asSubclass(IActionContext.class);
	}

}
