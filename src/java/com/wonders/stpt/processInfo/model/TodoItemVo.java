/**
 * 
 */
package com.wonders.stpt.processInfo.model;

/** 
 * @ClassName: InfoSearchVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午01:38:17 
 *  
 */
public class TodoItemVo {
	public int pageSize;
	public int page;
	
	public TodoItemVo(){
		this.pageSize = 10;
		this.page = 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	
	
}
