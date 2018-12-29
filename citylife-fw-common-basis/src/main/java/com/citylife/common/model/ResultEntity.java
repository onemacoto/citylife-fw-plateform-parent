package com.citylife.common.model;

import java.io.Serializable;

import com.citylife.common.message.MessageModel;
import com.citylife.common.message.Messages;

public class ResultEntity<T> implements ResultCode, Serializable {

  private static final long serialVersionUID = 1L;

  private String rtnCode = SUCCESS;
  
  private String traceId;


  private T body;
  
  private Messages messages = null;

  public ResultEntity(String rtnCode, T body) {
    this.rtnCode = rtnCode;
    this.body = body;
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

  public T getBody() {
    return body;
  }

  public void setBody(T body) {
    this.body = body;
  }

  public Messages getMessages() {
    return messages;
  }

  public void setMessages(Messages messages) {
    this.messages = messages;
  }

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }

  public static <R> ResultEntity<R> failure(String errorCode) {
    return new ResultEntity<>(errorCode);
  }

  public static <R> ResultEntity<R> failure(String errorCode, MessageModel message) {
    ResultEntity<R> entity = new ResultEntity<>(errorCode);
    entity.messages = Messages.build(message);
    return entity;
  }
  
  public static <R> ResultEntity<R> failure(String errorCode, Messages messages) {
    ResultEntity<R> entity = new ResultEntity<>(errorCode);
    entity.messages = messages;
    return entity;
  }


  public static <R> ResultEntity<R> ok() {
    return new ResultEntity<>(SUCCESS);
  }
  
  public static <R> ResultEntity<R> ok(R value) {
    ResultEntity<R> response =new ResultEntity<>(SUCCESS);
    response.setBody(value);
    return response;
  }
  
  public boolean hasError() {
    return ! rtnCode.equals(SUCCESS);
  }

}
