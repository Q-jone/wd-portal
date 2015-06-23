package com.wonders.stpt.beforeBuild.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_BEFORE_HISTORY")
public class History {
	private String id;
	private String projectName;
	private String projectId;
	private String routeName;
	private String routeId;
	private String areaName;
	private String areaId;
	private String typeName;
	private String typeId;
	private String monomerName;
	private String monomerId;
	private String paper;
	private String realFinishTime;
	private String result;
	private String removed;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String realStartTime;
	private String source;
	private String sourceId;
	private String deptId;
	private String paperId;
	private String status;
	private String planStartTime;
	private String planFinishTime;
	
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
	
	@Column(name = "PROJECT_NAME", length = 100)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Column(name = "PROJECT_ID", length = 32)
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
	
	@Column(name = "ROUTE_ID", length = 32)
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	
	@Column(name = "AREA_NAME", length = 100)
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Column(name = "AREA_ID", length = 32)
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	@Column(name = "TYPE_NAME", length = 100)
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Column(name = "TYPE_ID", length = 32)
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Column(name = "MONOMER_NAME", length = 100)
	public String getMonomerName() {
		return monomerName;
	}
	public void setMonomerName(String monomerName) {
		this.monomerName = monomerName;
	}
	
	@Column(name = "MONOMER_ID", length = 32)
	public String getMonomerId() {
		return monomerId;
	}
	public void setMonomerId(String monomerId) {
		this.monomerId = monomerId;
	}
	
	@Column(name = "PAPER", length = 100)
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	
	@Column(name = "REAL_FINISH_TIME", length = 50)
	public String getRealFinishTime() {
		return realFinishTime;
	}
	public void setRealFinishTime(String realFinishTime) {
		this.realFinishTime = realFinishTime;
	}
	
	@Column(name = "RESULT", length = 100)
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	@Column(name = "CREATE_TIME", length = 50)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "CREATE_USER", length = 50)
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	@Column(name = "UPDATE_TIME", length = 50)
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "UPDATE_USER", length = 50)
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	@Column(name = "REAL_START_TIME", length = 50)
	public String getRealStartTime() {
		return realStartTime;
	}
	public void setRealStartTime(String realStartTime) {
		this.realStartTime = realStartTime;
	}
	
	@Column(name = "SOURCE", length = 10)
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Column(name = "SOURCE_ID", length = 32)
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	@Column(name = "DEPT_ID", length = 32)
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	@Column(name = "PAPER_ID", length = 32)
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	@Column(name = "STATUS", length = 32)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "PLAN_START_TIME", length = 32)
	public String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	@Column(name = "PLAN_FINISH_TIME", length = 32)
	public String getPlanFinishTime() {
		return planFinishTime;
	}
	public void setPlanFinishTime(String planFinishTime) {
		this.planFinishTime = planFinishTime;
	}
	
}
