package com.citylife.common.model;

import java.util.HashMap;

public class AnyRequestData extends HashMap<String, Object> {
  private static final long serialVersionUID = 1L;

  @SuppressWarnings("unchecked")
  public <R> R get(String key) {
    return (R) super.get(key);
  }
}
