package com.wonders.stpt.exam.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_EXAM_GROUP")
public class ExamGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6752540394417305701L;

	private String id; //

	private String mainId; // 主表id

	private long groupNum = 1; // 题目组顺序号

	private String title; // 题目组名称

	private String groupDescription; // 题目组描述

	private String remark; // 备注

	private long removed = 0; // 删除

	private String loginName; //

	private String deptId; //

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

	// 主表id
	@Column(name = "MAIN_ID", nullable = true, length = 50)
	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	// 题目组顺序号
	@Column(name = "GROUP_NUM", nullable = true, length = 9)
	public long getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(long groupNum) {
		this.groupNum = groupNum;
	}

	// 题目组名称
	@Column(name = "TITLE", nullable = true, length = 200)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// 题目组描述
	@Column(name = "GROUP_DESCRIPTION", nullable = true, length = 1000)
	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	// 备注
	@Column(name = "REMARK", nullable = true, length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	// 删除
	@Column(name = "REMOVED", nullable = true, length = 9)
	public long getRemoved() {
		return removed;
	}

	public void setRemoved(long removed) {
		this.removed = removed;
	}

	//
	@Column(name = "LOGIN_NAME", nullable = true, length = 50)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	//
	@Column(name = "DEPT_ID", nullable = true, length = 50)
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}
