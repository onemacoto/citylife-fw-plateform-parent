package com.citylife.function.core.boot.template.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.citylife.common.component.JWTHelper;
import com.citylife.common.model.IUser;
import com.citylife.common.model.UserValueObject;
import com.citylife.common.utils.ExceptionUtils;

public class TemplateActionContextFactory<T> {

	@Autowired
	private JWTHelper jwtHelper;

	public <R extends IActionContext<T>> R createInstance(T parameter, String token, Class<R> cls) {
		try {
			R instance = cls.newInstance();
			instance.setParameter(parameter);
			IUser uvo = StringUtils.hasText(token) ? jwtHelper.parseToken(token, UserValueObject.class)
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
