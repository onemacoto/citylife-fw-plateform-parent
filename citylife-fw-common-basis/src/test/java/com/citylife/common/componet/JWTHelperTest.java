package com.citylife.common.componet;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import com.citylife.common.component.JWTHelper;
import com.citylife.common.config.StarterCommonBasisAutoConfig;
import com.citylife.common.model.IUser;
import com.citylife.common.model.UserValueObject;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes= StarterCommonBasisAutoConfig.class)
public class JWTHelperTest {

	@Autowired
	private JWTHelper jwtHelper;

	@Test
	public void testCreateToken01() {
		IUser user = new UserValueObject();
		user.setUserId("testId");
		System.out.println(jwtHelper.createToken(user));
		Assert.assertNotNull(jwtHelper.createToken(user));
	}

	@Test(expected = IllegalStateException.class)
	public void testCreateToken02() {
		Assert.assertNotNull(jwtHelper.createToken(null));
	}

	@Test
	public void testParseToken() {
		IUser user = new UserValueObject();
		user.setUserId("testId");
		IUser claims = jwtHelper.parseToken(jwtHelper.createToken(user), UserValueObject.class);
		Assert.assertThat(claims.getUserId(), CoreMatchers.is("testId"));
	}

}
