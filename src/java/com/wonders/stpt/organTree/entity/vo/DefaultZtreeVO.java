package com.wonders.stpt.organTree.entity.vo;

public class DefaultZtreeVO extends ZtreeVo{
	private String loginName;
	private String deptId;
	private String deptName;
	private String isRoot;
	
	
	
	
	public String getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(String isRoot) {
		this.isRoot = isRoot;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public DefaultZtreeVO(){
		
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	
}
