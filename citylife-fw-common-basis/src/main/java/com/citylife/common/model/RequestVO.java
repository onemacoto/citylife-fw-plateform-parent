package com.citylife.common.model;

public class RequestVO<T> {
  private T data;

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
}
