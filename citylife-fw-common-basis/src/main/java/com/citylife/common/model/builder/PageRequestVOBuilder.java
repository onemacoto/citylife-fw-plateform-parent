package com.citylife.common.model.builder;

import com.citylife.common.model.PageInfo;
import com.citylife.common.model.PageRequestVO;
import com.citylife.common.model.RequestVO;

public class PageRequestVOBuilder {
  
  public static <T> PageRequestVO<T> build(T parameter, PageInfo page) {
    return new PageRequestVO<>(parameter, page);
  }

  public static <T> PageRequestVO<T> build(T parameter, RequestVO<?> baseVO) {
    PageRequestVO<T> vo = new PageRequestVO<>(parameter);
    if (baseVO != null) {
      vo.setCommon(baseVO.getCommon());
      if (baseVO instanceof PageRequestVO) {
        vo.setPage(PageRequestVO.class.cast(baseVO).getPage());
      }
    }
    return vo;
  }


}
