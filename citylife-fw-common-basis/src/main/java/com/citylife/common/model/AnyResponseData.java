package com.citylife.common.model;

import java.util.HashMap;

public class AnyResponseData extends HashMap<String, Object> {

  private static final long serialVersionUID = 1L;
  
  public static AnyResponseData build() {
    return new AnyResponseData();
  }
  
  public AnyResponseData put(String key, Object value) {
    super.put(key, value);
    return this;
  }

  public AnyResponseData putAll(HashMap<String, Object> map) {
    super.putAll(map);
    return this;
  }

}
