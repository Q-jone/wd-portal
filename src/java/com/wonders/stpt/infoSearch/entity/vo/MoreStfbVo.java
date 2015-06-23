/**
 * 
 */
package com.wonders.stpt.infoSearch.entity.vo;

/** 
 * @ClassName: StfbVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-9 下午11:21:23 
 *  
 */
public class MoreStfbVo {
	private String SJ_ID;
	private String title;
	private String copyTitle;
	private String url;
	private String picUrl;
	private String createTime;
	private String partId;
	private String moreUrl;
	private String memo;
	private String copyMemo;
	private int sequence;
	private String name;
	
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCopyMemo() {
		return copyMemo;
	}
	public void setCopyMemo(String copyMemo) {
		this.copyMemo = copyMemo;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getSJ_ID() {
		return SJ_ID;
	}
	public void setSJ_ID(String sj_id) {
		SJ_ID = sj_id;
	}
	public String getMoreUrl() {
		return moreUrl;
	}
	public void setMoreUrl(String moreUrl) {
		this.moreUrl = moreUrl;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCopyTitle() {
		return copyTitle;
	}
	public void setCopyTitle(String copyTitle) {
		this.copyTitle = copyTitle;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
