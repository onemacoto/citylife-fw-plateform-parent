package com.citylife.common.model;

public class RequestVO<T> {
  private T data;

  private CommonInfo common;

  public RequestVO() {
  }

  public RequestVO(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public CommonInfo getCommon() {
    return common;
  }

  public void setCommon(CommonInfo common) {
    this.common = common;
  }
}
