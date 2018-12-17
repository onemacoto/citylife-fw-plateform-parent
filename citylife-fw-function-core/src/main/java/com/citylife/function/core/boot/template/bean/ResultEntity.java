package com.citylife.function.core.boot.template.bean;

import java.io.Serializable;

public class ResultEntity<T> implements FunctionResultCode, Serializable {

  private static final long serialVersionUID = 1L;

  private String rtnCode = SUCCESS;

  private T value;

  public ResultEntity(String rtnCode, T value) {
    this.rtnCode = rtnCode;
    this.value = value;
  }
  
  public ResultEntity(String rtnCode) {
    this(rtnCode, null);
  }

  public ResultEntity(T value) {
    this(SUCCESS, value);
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

  public static <R> ResultEntity<R> failure(String errorCode) {
    return new ResultEntity<>(errorCode);
  }

  public static <R> ResultEntity<R> ok() {
    return new ResultEntity<>(SUCCESS);
  }
  
  public static <R> ResultEntity<R> ok(R value) {
    ResultEntity<R> response =new ResultEntity<>(SUCCESS);
    response.setValue(value);
    return response;
  }
  
  public boolean hasError() {
    return ! rtnCode.equals(SUCCESS);
  }

}
