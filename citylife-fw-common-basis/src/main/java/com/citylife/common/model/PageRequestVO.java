package com.citylife.common.model;

public class PageRequestVO<T> extends RequestVO<T> {
  private PageInfo page;

  public PageRequestVO() {
    super();
  }

  public PageRequestVO(T data) {
    super(data);
  }

  public PageInfo getPage() {
    return page;
  }

  public void setPage(PageInfo page) {
    this.page = page;
  }
}
