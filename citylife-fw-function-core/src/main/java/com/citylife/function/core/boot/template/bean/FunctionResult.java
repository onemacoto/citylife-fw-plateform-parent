package com.citylife.function.core.boot.template.bean;

public class FunctionResult<T> implements FunctionResultCode {

  private String rtnCode = SUCCESS;

  private T value;

  public FunctionResult(String rtnCode) {
    this.rtnCode = rtnCode;
  }

  public String getRtnCode() {
    return rtnCode;
  }

  public void setRtnCode(String rtnCode) {
    this.rtnCode = rtnCode;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public static FunctionResult<?> failure(String errorCode) {
    return new FunctionResult<>(errorCode);
  }

  public static FunctionResult<?> success() {
    return new FunctionResult<>(SUCCESS);
  }
  
  public boolean hasError() {
    return rtnCode != SUCCESS;
  }

}
