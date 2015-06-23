package com.wonders.stpt.deptDoc.model.vo;

public class RecvQueryVo extends PageQueryVo{
	public String sourceCode_like;
	public String recvCode_like;
	public String sourceDept_like;
	public String title_like;
	public String recvDate_startDate;
	public String recvDate_endDate;
	
	public String getSourceCode_like() {
		return sourceCode_like;
	}
	public void setSourceCode_like(String sourceCode_like) {
		this.sourceCode_like = sourceCode_like;
	}
	public String getRecvCode_like() {
		return recvCode_like;
	}
	public void setRecvCode_like(String recvCode_like) {
		this.recvCode_like = recvCode_like;
	}
	public String getSourceDept_like() {
		return sourceDept_like;
	}
	public void setSourceDept_like(String sourceDept_like) {
		this.sourceDept_like = sourceDept_like;
	}
	public String getTitle_like() {
		return title_like;
	}
	public void setTitle_like(String title_like) {
		this.title_like = title_like;
	}
	public String getRecvDate_startDate() {
		return recvDate_startDate;
	}
	public void setRecvDate_startDate(String recvDate_startDate) {
		this.recvDate_startDate = recvDate_startDate;
	}
	public String getRecvDate_endDate() {
		return recvDate_endDate;
	}
	public void setRecvDate_endDate(String recvDate_endDate) {
		this.recvDate_endDate = recvDate_endDate;
	}
	
	
}
