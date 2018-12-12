package com.citylife.common.utils;

import java.util.Map;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class BeanMapper {
  private static final Mapper dozer = DozerBeanMapperBuilder.buildDefault();
  
  public static <T> T map(Object source, Class<T> destinationClass) {
    return source == null ? null : dozer.map(source, destinationClass);
  }
  
  @SuppressWarnings("unchecked")
  public static Map<String, Object> asMap(Object source) {
    return map(source, Map.class);
  }
  
  public static <T> T asBean(Map<String, ?> source, Class<T> clazz) {
    return map(source, clazz);
  }

}
