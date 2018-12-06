package com.citylife.function.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("citylife.function")
public class StarterFunctionProperties {
  private boolean enabled = true;

  private boolean mdcFilterEnabled = true;
  
  private boolean operationLogeerEnabled = true;
  
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
}
