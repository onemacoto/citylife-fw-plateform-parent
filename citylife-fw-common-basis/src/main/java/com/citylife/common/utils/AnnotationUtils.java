package com.citylife.common.utils;

import java.lang.annotation.Annotation;

import org.springframework.core.annotation.AnnotatedElementUtils;

public class AnnotationUtils {
  
  public static boolean isAnnotated(final Class<?> cls, final Class<? extends Annotation> annotationType) {
    return AnnotatedElementUtils.isAnnotated(cls, annotationType);
  }
   
  public static boolean isAnnotated(final Object target, final Class<? extends Annotation> annotationType) {
    return AnnotatedElementUtils.isAnnotated(target.getClass(), annotationType);
  }
  
  

}
