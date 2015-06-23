package com.wonders.stpt.beforeBuild.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_BEFORE_TASK")
public class Task {
	private String id;
	private String taskNum;
	private String taskName;
	private String planStartTime;
	private String planFinishTime;
	private String realStartTime;
	private String forecastFinishTime;
	private String realFinishTime;
	private String status;
	private String warnTime;
	private String mainPerson;
	private String phone;
	private String updatePerson;
	private String updateTime;
	private String monomerPlanId;
	private String remark;
	private String createTime;
	private String createUser;
	private String removed;
	private String biaodiwu;
	private String oldDeptId;
	private String invalidStartTime;
	private String invalidFinishTime;
	
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
	
	@Column(name = "TASK_NUM", length = 100)
	public String getTaskNum() {
		return taskNum;
	}
	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}
	
	@Column(name = "TASK_NAME", length = 100)
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	@Column(name = "PLAN_START_TIME", length = 100)
	public String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	
	@Column(name = "PLAN_FINISH_TIME", length = 100)
	public String getPlanFinishTime() {
		return planFinishTime;
	}
	public void setPlanFinishTime(String planFinishTime) {
		this.planFinishTime = planFinishTime;
	}
	
	@Column(name = "REAL_START_TIME", length = 100)
	public String getRealStartTime() {
		return realStartTime;
	}
	public void setRealStartTime(String realStartTime) {
		this.realStartTime = realStartTime;
	}
	
	@Column(name = "FORECAST_FINISH_TIME", length = 100)
	public String getForecastFinishTime() {
		return forecastFinishTime;
	}
	public void setForecastFinishTime(String forecastFinishTime) {
		this.forecastFinishTime = forecastFinishTime;
	}
	
	@Column(name = "REAL_FINISH_TIME", length = 100)
	public String getRealFinishTime() {
		return realFinishTime;
	}
	public void setRealFinishTime(String realFinishTime) {
		this.realFinishTime = realFinishTime;
	}
	
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "WARN_TIME", length = 100)
	public String getWarnTime() {
		return warnTime;
	}
	public void setWarnTime(String warnTime) {
		this.warnTime = warnTime;
	}
	
	@Column(name = "MAIN_PERSON", length = 100)
	public String getMainPerson() {
		return mainPerson;
	}
	public void setMainPerson(String mainPerson) {
		this.mainPerson = mainPerson;
	}
	
	@Column(name = "PHONE", length = 100)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "UPDATE_PERSON", length = 100)
	public String getUpdatePerson() {
		return updatePerson;
	}
	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
	
	@Column(name = "UPDATE_TIME", length = 100)
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "MONOMER_PLAN_ID", length = 100)
	public String getMonomerPlanId() {
		return monomerPlanId;
	}
	public void setMonomerPlanId(String monomerPlanId) {
		this.monomerPlanId = monomerPlanId;
	}
	
	@Column(name = "REMARK", length = 4000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "CREATE_TIME", length = 100)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "CREATE_USER", length = 100)
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	@Column(name = "BIAODIWU", length = 100)
	public String getBiaodiwu() {
		return biaodiwu;
	}
	public void setBiaodiwu(String biaodiwu) {
		this.biaodiwu = biaodiwu;
	}
	
	@Column(name = "OLD_DEPT_ID", length = 100)
	public String getOldDeptId() {
		return oldDeptId;
	}
	public void setOldDeptId(String oldDeptId) {
		this.oldDeptId = oldDeptId;
	}
	
	@Column(name = "INVALID_START_TIME", length = 100)
	public String getInvalidStartTime() {
		return invalidStartTime;
	}
	public void setInvalidStartTime(String invalidStartTime) {
		this.invalidStartTime = invalidStartTime;
	}
	
	@Column(name = "INVALID_FINISH_TIME", length = 100)
	public String getInvalidFinishTime() {
		return invalidFinishTime;
	}
	public void setInvalidFinishTime(String invalidFinishTime) {
		this.invalidFinishTime = invalidFinishTime;
	}
	
	
}
