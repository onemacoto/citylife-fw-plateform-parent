package com.citylife.common.model.builder;

import com.citylife.common.model.RequestVO;

public class RequestVOBuilder {

  public static <T> RequestVO<T> build(T parameter) {
    return new RequestVO<>(parameter);
  }

}
