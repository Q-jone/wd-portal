/**
 * 
 */
package com.wonders.stpt.infoSearch.entity.vo;

/** 
 * @ClassName: InfoSearchVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午01:38:17 
 *  
 */
public class InfoSearchVo {
	private String sj_id;
	private String part_id;
	private String searchWord;
	private int pageSize;
	private int page;
	
	public InfoSearchVo(){
		this.sj_id="";
		this.part_id="";
		this.searchWord="";
		this.pageSize = 10;
		this.page = 1;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSj_id() {
		return sj_id;
	}
	public void setSj_id(String sj_id) {
		this.sj_id = sj_id;
	}
	public String getPart_id() {
		return part_id;
	}
	public void setPart_id(String part_id) {
		this.part_id = part_id;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
