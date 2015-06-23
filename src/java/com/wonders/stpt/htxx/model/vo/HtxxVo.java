/**
 * 
 */
package com.wonders.stpt.htxx.model.vo;

/** 
 * @ClassName: HtxxVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-5-15 下午2:31:54 
 *  
 */
public class HtxxVo {
	public int pageSize;
	public int page;
	
	public HtxxVo(){
		this.pageSize = 20;
		this.page = 1;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	
	
}
