package com.citylife.common.component;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.github.dozermapper.core.Mapper;

public class BeanMapper {
  
  @Autowired
  private Mapper dozer;

  public  <T> T map(Object source, Class<T> destinationClass) {
    return source == null ? null : dozer.map(source, destinationClass);
  }
  
  @SuppressWarnings("unchecked")
  public Map<String, Object> asMap(Object source) {
    return map(source, Map.class);
  }
  
  public <T> T asBean(Map<String, ?> source, Class<T> clazz) {
    return map(source, clazz);
  }
}
