package com.citylife.common.exception;

public class MethodNotSupportedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public MethodNotSupportedException(final String message) {
    super(message);
  }

}
