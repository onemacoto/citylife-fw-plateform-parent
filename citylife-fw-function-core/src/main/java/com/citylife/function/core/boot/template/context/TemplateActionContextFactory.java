package com.citylife.function.core.boot.template.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.citylife.common.component.JWTHelper;
import com.citylife.common.model.IHeaderUser;
import com.citylife.common.model.UserValueObject;
import com.citylife.common.utils.ExceptionUtils;

public class TemplateActionContextFactory<T> {

	@Autowired
	private JWTHelper jwtHelper;

	public <R extends IActionContext<T>> R createInstance(String version, T parameter, String token, Class<R> cls) {
		try {
			R instance = cls.newInstance();
			instance.setVersion(version);
			instance.setParameter(parameter);
			IHeaderUser uvo = StringUtils.hasText(token) ? jwtHelper.parseToken(token, UserValueObject.class)
					: UserValueObject.empty();
			instance.setUser(uvo);
			instance.setToken(token);
			return instance;
		} catch (Exception e) {
			ExceptionUtils.wrapAndThrow(e);
		}
		return null;
	}
}
