/**
 * 
 */
package com.wonders.stpt.todoItem.model.vo;

/** 
 * @ClassName: InfoSearchVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午01:38:17 
 *  
 */
public class TodoItemVo {
	private final String split = "#";
	public String typename;
	public String title;
	public String applydept;
	public String occurtimes;
	public String occurtimee;
	public int pageSize;
	public int page;
	
	public TodoItemVo(){
		this.pageSize = 10;
		this.page = 1;
	}

	
	public String getOccurtimes() {
		return occurtimes;
	}


	public void setOccurtimes(String occurtimes) {
		this.occurtimes = occurtimes;
	}


	public String getOccurtimee() {
		return occurtimee;
	}


	public void setOccurtimee(String occurtimee) {
		this.occurtimee = occurtimee;
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

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApplydept() {
		return applydept;
	}

	public void setApplydept(String applydept) {
		this.applydept = applydept;
	}

	public String getOccurtime() {
		return occurtimes + split + occurtimee;
	}


	
	
}
