package com.citylife.function.core.utils;

import com.citylife.function.core.exception.MethodNotSupportedException;
import com.citylife.function.core.exception.SystemRuntimeException;

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
