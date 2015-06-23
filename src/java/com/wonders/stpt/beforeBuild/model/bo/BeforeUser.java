package com.wonders.stpt.beforeBuild.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_BEFORE_USER")
public class BeforeUser {
	private String id;
	private String role;
	private String name;
	private String oldDeptId;
	private String phone;
	private String removed;
	private String deptName;
	
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
	
	@Column(name = "ROLE", length = 1)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Column(name = "NAME", length = 32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "OLD_DEPT_ID", length = 32)
	public String getOldDeptId() {
		return oldDeptId;
	}
	public void setOldDeptId(String oldDeptId) {
		this.oldDeptId = oldDeptId;
	}
	
	@Column(name = "PHONE", length = 32)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	@Column(name = "DEPT_NAME", length = 32)
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
