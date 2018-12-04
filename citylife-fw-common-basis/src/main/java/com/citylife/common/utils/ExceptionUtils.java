package com.citylife.common.utils;

import com.citylife.common.exception.MethodNotSupportedException;
import com.citylife.common.exception.SystemRuntimeException;

public class ExceptionUtils {

  public static void wrapAndThrow(final Throwable throwable) {
    if (throwable instanceof RuntimeException) {
        throw (RuntimeException) throwable;
    }

    throw new SystemRuntimeException(throwable);
  }
  
  public static MethodNotSupportedException throwMethodNotSupportedException(String methodName) {
    throw new MethodNotSupportedException(String.format("the method [%s] is not supported", methodName));
  } 

}
