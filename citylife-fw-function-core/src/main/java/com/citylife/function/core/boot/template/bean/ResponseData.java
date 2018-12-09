package com.citylife.function.core.boot.template.bean;

public class ResponseData<T> implements FunctionResultCode {

  private String rtnCode = SUCCESS;

  private T value;

  public ResponseData(String rtnCode, T value) {
    this.rtnCode = rtnCode;
    this.value = value;
  }
  
  public ResponseData(String rtnCode) {
    this(rtnCode, null);
  }

  public ResponseData(T value) {
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

  public static ResponseData<?> failure(String errorCode) {
    return new ResponseData<>(errorCode);
  }

  public static ResponseData<?> ok() {
    return new ResponseData<>(SUCCESS);
  }
  
  public static <R> ResponseData<R> ok(R value) {
    ResponseData<R> response =new ResponseData<>(SUCCESS);
    response.setValue(value);
    return response;
  }
  
  public boolean hasError() {
    return rtnCode != SUCCESS;
  }

}
