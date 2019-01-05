package com.citylife.common.model.builder;

import com.citylife.common.model.ResponseVO;

public class ResponseVOBuilder {

  public static <T> ResponseVO<T> build(T parameter) {
    return new ResponseVO<>(parameter);
  }

}
