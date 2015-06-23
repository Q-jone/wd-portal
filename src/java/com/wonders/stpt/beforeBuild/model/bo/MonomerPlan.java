package com.wonders.stpt.beforeBuild.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_BEFORE_MONOMER_PLAN")
public class MonomerPlan {
	private String id;
	private String planNum;
	private String planName;
	private String monomerTypeName;
	private String monomerTypeId;
	private String monomerName;
	private String monomerId;
	private String paperName;
	private String paperId;
	private String ifNode;
	private String ifMilestone;
	private String planStartTime;
	private String planFinishTime;
	private String status;
	private String warnDays;
	private String mainPerson;
	private String phone;
	private String remark;
	private String linePlanId;
	private String ruleVersionId;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String removed;
	private String routeName;
	private String routeId;
	private String oldDeptId;
	private int planOrder;
	private String checkStatus;
	
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
	
	@Column(name = "MONOMER_TYPE_NAME", length = 100)
	public String getMonomerTypeName() {
		return monomerTypeName;
	}
	public void setMonomerTypeName(String monomerTypeName) {
		this.monomerTypeName = monomerTypeName;
	}
	
	@Column(name = "MONOMER_TYPE_ID", length = 100)
	public String getMonomerTypeId() {
		return monomerTypeId;
	}
	public void setMonomerTypeId(String monomerTypeId) {
		this.monomerTypeId = monomerTypeId;
	}
	
	@Column(name = "MONOMER_NAME", length = 100)
	public String getMonomerName() {
		return monomerName;
	}
	public void setMonomerName(String monomerName) {
		this.monomerName = monomerName;
	}
	
	@Column(name = "MONOMER_ID", length = 100)
	public String getMonomerId() {
		return monomerId;
	}
	public void setMonomerId(String monomerId) {
		this.monomerId = monomerId;
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
	
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "WARN_DAYS", length = 100)
	public String getWarnDays() {
		return warnDays;
	}
	public void setWarnDays(String warnDays) {
		this.warnDays = warnDays;
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
	
	@Column(name = "REMARK", length = 4000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "LINE_PLAN_ID", length = 100)
	public String getLinePlanId() {
		return linePlanId;
	}
	public void setLinePlanId(String linePlanId) {
		this.linePlanId = linePlanId;
	}
	
	@Column(name = "RULE_VERSION_ID", length = 100)
	public String getRuleVersionId() {
		return ruleVersionId;
	}
	public void setRuleVersionId(String ruleVersionId) {
		this.ruleVersionId = ruleVersionId;
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
	
	@Column(name = "CHECK_STATUS", length = 1)
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	
}
