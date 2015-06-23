package com.wonders.stpt.exam.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wonders.stpt.exam.util.ExamUtil;

@Entity
@Table(name = "T_EXAM_MAIN")
public class ExamMain implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 7431443812191330615L;
	private String id; 
	private String title;//          VARCHAR2(500),
	private long state = 0l;//      NUMBER default 0,状态位 0进行中1 完成
	private String cUser;//         VARCHAR2(20), 发起人
	private String cTime = ExamUtil.getNowTime();//         VARCHAR2(19), 发起时间
	private String endTime ;//      VARCHAR2(19), 结束时间
	private String remark;//         VARCHAR2(2000),
	private long removed= 0l;//        NUMBER default 0,
	private String exType;//        VARCHAR2(100),  问卷类型
	
	private String startGroup;//    VARCHAR2(200), 对应小提醒及调查问卷   发起组织
	private String exDescription;// VARCHAR2(1000),对应小提醒内容
	private String targetName;//    VARCHAR2(200)对应小提醒  面向对象
	private String desp;//    VARCHAR2(1000)对应小提醒  背景描述
	
	private String loginName;//     VARCHAR2(50),
	private String deptId;//        VARCHAR2(50),
	
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
	
	@Column(name = "title", nullable = true, length = 500)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "state", nullable = true, length = 2)
	public long getState() {
		return state;
	}
	public void setState(long state) {
		this.state = state;
	}
	
	@Column(name = "c_user", nullable = true, length = 20)
	public String getcUser() {
		return cUser;
	}
	public void setcUser(String cUser) {
		this.cUser = cUser;
	}
	@Column(name = "c_time", nullable = true, length = 20)
	public String getcTime() {
		return cTime;
	}
	public void setcTime(String cTime) {
		this.cTime = cTime;
	}
	
	@Column(name = "desp", nullable = true, length = 1000)
	public String getDesp() {
		return desp;
	}
	public void setDesp(String desp) {
		this.desp = desp;
	}
	@Column(name = "end_time", nullable = true, length = 20)
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(name = "remark", nullable = true, length = 500)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "removed", nullable = true, length = 2)
	public long getRemoved() {
		return removed;
	}
	public void setRemoved(long removed) {
		this.removed = removed;
	}
	@Column(name = "ex_type", nullable = true, length = 1000)
	public String getExType() {
		return exType;
	}
	public void setExType(String exType) {
		this.exType = exType;
	}
	@Column(name = "start_group", nullable = true, length = 200)
	public String getStartGroup() {
		return startGroup;
	}
	public void setStartGroup(String startGroup) {
		this.startGroup = startGroup;
	}
	@Column(name = "ex_description", nullable = true, length = 1000)
	public String getExDescription() {
		return exDescription;
	}
	public void setExDescription(String exDescription) {
		this.exDescription = exDescription;
	}
	@Column(name = "target_name", nullable = true, length = 200)
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	@Column(name = "login_name", nullable = true, length = 50)
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	@Column(name = "dept_id", nullable = true, length = 50)
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	

}
