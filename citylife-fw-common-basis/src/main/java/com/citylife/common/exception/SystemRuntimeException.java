package com.citylife.common.exception;

public class SystemRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public SystemRuntimeException(final String message) {
    super(message);
  }

  public SystemRuntimeException(final String message, final Throwable cause) {
    super(message, cause);
  }
  
  public SystemRuntimeException(final Throwable cause) {
    super(cause);
  }
}