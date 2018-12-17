package com.citylife.function.core.boot.template.bean;

import java.util.HashMap;

public class AnyRequest extends HashMap<String, Object> {

  private static final long serialVersionUID = 1L;
  
  @SuppressWarnings("unchecked")
  public <R> R get(String key) {
    return (R) super.get(key);
  }

}
