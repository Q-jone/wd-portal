/**
 * 
 */
package com.wonders.stpt.core.login.entity.vo;

/** 
 * @ClassName: DeptVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-20 下午01:30:11 
 *  
 */
public class DeptVo implements java.io.Serializable {
	private String userId;
	private String deptId;
	private String deptName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	
}
