package com.wonders.stpt.core.cfconsole.entity.vo;

import java.util.List;

public class UserVo {
	private List<String> loginName;
	private List<String> userName;
	private String deptId;
	private String orders;
	private List<String> group;
	private String password;
	
	public UserVo(){
		this.password = "888888";
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getLoginName() {
		return loginName;
	}
	public void setLoginName(List<String> loginName) {
		this.loginName = loginName;
	}
	public List<String> getUserName() {
		return userName;
	}
	public void setUserName(List<String> userName) {
		this.userName = userName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getOrders() {
		return orders;
	}
	public void setOrders(String orders) {
		this.orders = orders;
	}
	public List<String> getGroup() {
		return group;
	}
	public void setGroup(List<String> group) {
		this.group = group;
	}
	
	
}
