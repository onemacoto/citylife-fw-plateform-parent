package com.citylife.function.core.boot.template.bean;

import java.util.HashMap;

public class AnyResponse extends HashMap<String, Object> {

  private static final long serialVersionUID = 1L;
  
  public static AnyResponse build() {
    return new AnyResponse();
  }
  
  public AnyResponse put(String key, Object value) {
    super.put(key, value);
    return this;
  }

  public AnyResponse putAll(HashMap<String, Object> map) {
    super.putAll(map);
    return this;
  }

}
