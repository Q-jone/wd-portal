package com.wonders.stpt.deptDoc.model.vo;

public class RedvQueryVo extends PageQueryVo{
	public String deptCode_like;
	public String addDept_like;
	public String addDate_startDate;
	public String addDate_endDate;
	public String title_like;
	
	public String getDeptCode_like() {
		return deptCode_like;
	}
	public void setDeptCode_like(String deptCode_like) {
		this.deptCode_like = deptCode_like;
	}
	public String getAddDept_like() {
		return addDept_like;
	}
	public void setAddDept_like(String addDept_like) {
		this.addDept_like = addDept_like;
	}
	public String getAddDate_startDate() {
		return addDate_startDate;
	}
	public void setAddDate_startDate(String addDate_startDate) {
		this.addDate_startDate = addDate_startDate;
	}
	public String getAddDate_endDate() {
		return addDate_endDate;
	}
	public void setAddDate_endDate(String addDate_endDate) {
		this.addDate_endDate = addDate_endDate;
	}
	public String getTitle_like() {
		return title_like;
	}
	public void setTitle_like(String title_like) {
		this.title_like = title_like;
	}
	
	
}
