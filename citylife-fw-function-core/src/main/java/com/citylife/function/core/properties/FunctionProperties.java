package com.citylife.function.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("citylife.function")
public class FunctionProperties {

  private boolean enabled = true;

  private boolean mdcFilterEnabled = true;
  
  private boolean operationLogeerEnabled = true;

  private boolean userArgumentResolverEnabled = true;

  private boolean mockUserBuilderEnabled = false;
  
  private MockUserProperties mockUser = new MockUserProperties();

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isMdcFilterEnabled() {
    return mdcFilterEnabled;
  }

  public void setMdcFilterEnabled(boolean mdcFilterEnabled) {
    this.mdcFilterEnabled = mdcFilterEnabled;
  }

  public boolean isOperationLogeerEnabled() {
    return operationLogeerEnabled;
  }

  public void setOperationLogeerEnabled(boolean operationLogeerEnabled) {
    this.operationLogeerEnabled = operationLogeerEnabled;
  }

  public boolean isUserArgumentResolverEnabled() {
    return userArgumentResolverEnabled;
  }

  public void setUserArgumentResolverEnabled(boolean userArgumentResolverEnabled) {
    this.userArgumentResolverEnabled = userArgumentResolverEnabled;
  }

  public boolean isMockUserBuilderEnabled() {
    return mockUserBuilderEnabled;
  }

  public void setMockUserBuilderEnabled(boolean mockUserBuilderEnabled) {
    this.mockUserBuilderEnabled = mockUserBuilderEnabled;
  }
  
  public MockUserProperties getMockUser() {
    return mockUser;
  }

  public void setMockUser(MockUserProperties mockUser) {
    this.mockUser = mockUser;
  }

  public static class MockUserProperties {
    private String userId;

    public String getUserId() {
      return userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }
    
    
  }
  
}
