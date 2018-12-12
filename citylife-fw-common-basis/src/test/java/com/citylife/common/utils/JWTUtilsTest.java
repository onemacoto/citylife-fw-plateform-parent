package com.citylife.common.utils;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.citylife.common.model.IUser;
import com.citylife.common.model.UserValueObject;


public class JWTUtilsTest {
  
  @Test
  public void testCreateToken01() {
    IUser user = new UserValueObject();
    user.setUserId("testId");
    System.out.println(JWTUtils.createToken(user));
    Assert.assertNotNull(JWTUtils.createToken(user));
  }

  @Test(expected =IllegalStateException.class)
  public void testCreateToken02() {
    Assert.assertNotNull(JWTUtils.createToken(null));
  }

  
  @Test
  public void testParseToken() {
    IUser user = new UserValueObject();
    user.setUserId("testId");
    IUser claims =  JWTUtils.parseToken(JWTUtils.createToken(user), UserValueObject.class);
    Assert.assertThat(claims.getUserId(), CoreMatchers.is("testId"));
  }

}
