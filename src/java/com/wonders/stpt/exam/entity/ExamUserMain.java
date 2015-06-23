package com.wonders.stpt.exam.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "T_EXAM_UMAIN")
public class ExamUserMain  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8431097001421067256L;

	private String id; //

	private String mainId; //

	private long state; // 0再填 1完成

	private String beginTime; // 开始时间

	private String endTime; // 完成时间

	private String remark; // 备注

	private long removed; //

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

	//
	@Column(name = "MAIN_ID", nullable = true, length = 50)
	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	// 0再填 1完成
	@Column(name = "STATE", nullable = true, length = 9)
	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

	// 开始时间
	@Column(name = "BEGIN_TIME", nullable = true, length = 19)
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	// 完成时间
	@Column(name = "END_TIME", nullable = true, length = 19)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	// 备注
	@Column(name = "REMARK", nullable = true, length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	//
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
