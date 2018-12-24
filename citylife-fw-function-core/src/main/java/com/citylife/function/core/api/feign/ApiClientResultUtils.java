package com.citylife.function.core.api.feign;

import com.citylife.common.model.ResultEntity;
import com.citylife.function.core.exception.ApiResultRuntimeExeption;

public class ApiClientResultUtils {
  
  public static void validate(ResultEntity<?> result) {
    if (result.hasError()) {
      throw ApiResultRuntimeExeption.transfer(result);
    }
  }

}
