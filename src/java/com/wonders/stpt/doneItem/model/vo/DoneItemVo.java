/**
 * 
 */
package com.wonders.stpt.doneItem.model.vo;

/** 
 * @ClassName: InfoSearchVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-11 下午01:38:17 
 *  
 */
public class DoneItemVo {
	private final String split = "#";
	public String processname;
	public String summary;
	public String username;
	public String starttimes;
	public String starttimee;
	public String endtimes;
	public String endtimee;
	public String deptname;
	public String status;
	public int pageSize;
	public int page;
	public String days;
	
	
	
	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DoneItemVo(){
		this.pageSize = 10;
		this.page = 1;
		this.days = "30";
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

	public String getProcessname() {
		return processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStarttime() {
		return starttimes + split + starttimee;
	}

	

	public String getEndtime() {
		return endtimes + split + endtimee;
	}

	
	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getStarttimes() {
		return starttimes;
	}

	public void setStarttimes(String starttimes) {
		this.starttimes = starttimes;
	}

	public String getStarttimee() {
		return starttimee;
	}

	public void setStarttimee(String starttimee) {
		this.starttimee = starttimee;
	}

	public String getEndtimes() {
		return endtimes;
	}

	public void setEndtimes(String endtimes) {
		this.endtimes = endtimes;
	}

	public String getEndtimee() {
		return endtimee;
	}

	public void setEndtimee(String endtimee) {
		this.endtimee = endtimee;
	}

	


	
	
}
