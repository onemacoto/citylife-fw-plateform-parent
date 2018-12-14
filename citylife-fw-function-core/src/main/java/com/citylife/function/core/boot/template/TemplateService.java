package com.citylife.function.core.boot.template;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.citylife.function.core.boot.template.bean.ResultEntity;
import com.citylife.function.core.boot.template.context.IActionContext;

public class TemplateService {

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public <P, R> ResultEntity<R> excute(final ITemplateAciton<P, R> action, final P parameter, final String token) {
		return excuteWithoutTransaction(action, parameter, token);
	}

	public <P, R> ResultEntity<R> excuteWithoutTransaction(final ITemplateAciton<P, R> action, final P parameter,
			final String token) {
		IActionContext<P> context = action.createContext(parameter, token);
		ResultEntity<R> result = action.validate(context);
		if (result.hasError()) {
			return result;
		}
		return action.execute(context);
	}

}
