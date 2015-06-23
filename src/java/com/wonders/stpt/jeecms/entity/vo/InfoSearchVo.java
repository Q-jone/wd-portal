package com.wonders.stpt.jeecms.entity.vo;

/** 
 * @ClassName: InfoSearchVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author huzhiming
 * @date 2012-1-10 下午14:13:17 
 *  
 */
public class InfoSearchVo{
	private String channelId;
	private String searchWord;
	private int pageSize;
	private int page;
	
	public InfoSearchVo(){
		this.channelId="";
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
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId =channelId;
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