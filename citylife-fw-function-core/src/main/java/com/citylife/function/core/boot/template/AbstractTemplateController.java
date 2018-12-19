package com.citylife.function.core.boot.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import com.citylife.common.logging.AdminLogger;
import com.citylife.common.model.ResultEntity;
import com.citylife.common.utils.AnnotationUtils;
import com.citylife.function.core.annotations.ActionTransactional;
import com.citylife.function.core.log.IOperationLogger;

public class AbstractTemplateController<S extends TemplateService> {

	private S service;

	@Autowired
	public void setService(S service) {
		this.service = service;
	}

	@Autowired(required = false)
	IOperationLogger operationLogger;

	protected <P, R> ResultEntity<R> doAction(final ITemplateAciton<P, R> action, final P parameter,
			final String token) {

		if (operationLogger != null) {
			operationLogger.logOperationBegin(action);
		}

		StopWatch sw = new StopWatch();
		try {
			sw.start();
			ResultEntity<R> result = null;
			if (AnnotationUtils.isAnnotated(action, ActionTransactional.class)) {
				result = service.excute(action, parameter, token);
			} else {
				result = service.excuteWithoutTransaction(action, parameter, token);
			}
			return result;
		} catch (Throwable t) {
			AdminLogger.error(String.format("exception happen when call action [%s]",
					(action == null) ? "undefined action" : action.getActionName()), t);
			return ResultEntity.failure(ResultEntity.SYSTEM_ERROR);
		} finally {
			sw.stop();
			if (operationLogger != null) {
				operationLogger.logOperationEnd(action, sw.getTotalTimeMillis());
			}
		}

	}

}
