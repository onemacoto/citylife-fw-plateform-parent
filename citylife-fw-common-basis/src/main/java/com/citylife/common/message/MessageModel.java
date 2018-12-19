package com.citylife.common.message;

import java.io.Serializable;

public class MessageModel implements Serializable {
  
  public static final String WARN = "warn";
  public static final String INFO = "info";
  public static final String ERROR = "error";

  private static final long serialVersionUID = 1L;
  private String type;
  private String message;

  private MessageModel(String type, String message) {
    this.type = type;
    this.message = message;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  public static MessageModel warn(String message) {
    return new MessageModel(WARN, message);
  }

  public static MessageModel info(String message) {
    return new MessageModel(INFO, message);
  }

  public static MessageModel error(String message) {
    return new MessageModel(ERROR, message);
  }
  
}
