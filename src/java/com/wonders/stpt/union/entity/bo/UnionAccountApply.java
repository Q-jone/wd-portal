package com.wonders.stpt.union.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "U_ACCOUNT_APPLY")
public class UnionAccountApply implements Serializable{
	
    private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 单位主键
	 */
	private String deptId;
	/**
	 * 单位名称
	 */
	private String deptName;
	
	private int status = 0;
    
    private long removed= 0l;
	private String cUser;//         VARCHAR2(20), 发起人
	private String cTime;//         VARCHAR2(19), 发起时间
	private String uUser;//         VARCHAR2(20), 更新人
	private String uTime;//         VARCHAR2(19), 更新时间
    
	private String remark;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "STATUS", nullable = true, length = 1)
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "NAME", nullable = true, length = 32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "LOGIN_NAME", nullable = true, length = 32)
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Column(name = "c_user", nullable = true, length = 40)
	public String getcUser() {
		return cUser;
	}
	public void setcUser(String cUser) {
		this.cUser = cUser;
	}
	@Column(name = "c_time", nullable = true, length = 40)
	public String getcTime() {
		return cTime;
	}
	public void setcTime(String cTime) {
		this.cTime = cTime;
	}
	@Column(name = "u_user", nullable = true, length = 40)
	public String getuUser() {
		return uUser;
	}
	public void setuUser(String uUser) {
		this.uUser = uUser;
	}
	@Column(name = "u_time", nullable = true, length = 40)
	public String getuTime() {
		return uTime;
	}
	public void setuTime(String uTime) {
		this.uTime = uTime;
	}
	
	@Column(name = "removed", nullable = true, length = 1)
	public long getRemoved() {
		return removed;
	}

	public void setRemoved(long removed) {
		this.removed = removed;
	}

	@Column(name = "DEPT_ID", nullable = true, length = 40)
    public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	@Column(name = "DEPT_NAME", nullable = true, length = 200)
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Column(name = "REMARK", nullable = true, length = 1000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
