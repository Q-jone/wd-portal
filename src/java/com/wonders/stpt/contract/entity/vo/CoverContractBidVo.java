package com.wonders.stpt.contract.entity.vo;

import java.util.List;

public class CoverContractBidVo {

	private List<String> categories;
	private List<Long> bid1List;
	private List<Long> bid2List;
	private List<Long> bid3List;
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public List<Long> getBid1List() {
		return bid1List;
	}
	public void setBid1List(List<Long> bid1List) {
		this.bid1List = bid1List;
	}
	public List<Long> getBid2List() {
		return bid2List;
	}
	public void setBid2List(List<Long> bid2List) {
		this.bid2List = bid2List;
	}
	public List<Long> getBid3List() {
		return bid3List;
	}
	public void setBid3List(List<Long> bid3List) {
		this.bid3List = bid3List;
	}
	
}
