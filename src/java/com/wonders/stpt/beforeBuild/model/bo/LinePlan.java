package com.wonders.stpt.beforeBuild.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_BEFORE_LINE_PLAN")
public class LinePlan {
	private String id;
	private String planNum;
	private String planName;
	private String projectName;
	private String projectId;
	private String routeName;
	private String routeId;
	private String planTypeName;
	private String planTypeId;
	private String ifNode;
	private String ifMilestone;
	private String paperName;
	private String paperId;
	private String planStartTime;
	private String planFinishTime;
	private String timeLimit;
	private String workload;
	private String measure;
	private String status;
	private String remark;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String removed;
	private String oldDeptId;
	private int planOrder;
	
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
	
	@Column(name = "PLAN_NUM", length = 100)
	public String getPlanNum() {
		return planNum;
	}
	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}
	
	@Column(name = "PLAN_NAME", length = 100)
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	@Column(name = "PROJECT_NAME", length = 100)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Column(name = "PROJECT_ID", length = 100)
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Column(name = "ROUTE_NAME", length = 100)
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	
	@Column(name = "ROUTE_ID", length = 100)
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	
	@Column(name = "PLAN_TYPE_NAME", length = 100)
	public String getPlanTypeName() {
		return planTypeName;
	}
	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}
	
	@Column(name = "PLAN_TYPE_ID", length = 100)
	public String getPlanTypeId() {
		return planTypeId;
	}
	public void setPlanTypeId(String planTypeId) {
		this.planTypeId = planTypeId;
	}
	
	@Column(name = "IF_NODE", length = 1)
	public String getIfNode() {
		return ifNode;
	}
	public void setIfNode(String ifNode) {
		this.ifNode = ifNode;
	}
	
	@Column(name = "IF_MILESTONE", length = 1)
	public String getIfMilestone() {
		return ifMilestone;
	}
	public void setIfMilestone(String ifMilestone) {
		this.ifMilestone = ifMilestone;
	}
	
	@Column(name = "PAPER_NAME", length = 100)
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	
	@Column(name = "PAPER_ID", length = 100)
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
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
	
	@Column(name = "TIME_LIMIT", length = 100)
	public String getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	@Column(name = "WORKLOAD", length = 100)
	public String getWorkload() {
		return workload;
	}
	public void setWorkload(String workload) {
		this.workload = workload;
	}
	
	@Column(name = "MEASURE", length = 100)
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	@Column(name = "UPDATE_TIME", length = 100)
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "UPDATE_USER", length = 100)
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	@Column(name = "OLD_DEPT_ID", length = 100)
	public String getOldDeptId() {
		return oldDeptId;
	}
	public void setOldDeptId(String oldDeptId) {
		this.oldDeptId = oldDeptId;
	}
	
	@Column(name = "PLAN_ORDER")
	public int getPlanOrder() {
		return planOrder;
	}
	public void setPlanOrder(int planOrder) {
		this.planOrder = planOrder;
	}
	
}
