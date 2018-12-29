package com.citylife.common.model;

public class PageResponseVO<T> extends ResponseVO<T> {
	private PageInfo page;
	public PageInfo getPage() {
		return page;
	}
	public void setPage(PageInfo page) {
		this.page = page;
	}
}
