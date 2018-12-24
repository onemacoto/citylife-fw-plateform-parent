package com.citylife.function.core.exception;

import com.citylife.common.exception.SystemRuntimeException;
import com.citylife.common.model.ResultEntity;

public class ApiResultRuntimeExeption extends SystemRuntimeException {

  private static final long serialVersionUID = 1L;

  private ResultEntity<?> result;

  public ApiResultRuntimeExeption(ResultEntity<?> result) {
    super("Api Cleint Exception");
    this.result = result;
  }

  public <T> ResultEntity<T> getResult() {
    return ResultEntity.failure(result.getRtnCode(), result.getMessages());
  }

  public static ApiResultRuntimeExeption transfer(ResultEntity<?> result) {
    return new ApiResultRuntimeExeption(result);
  }

}
