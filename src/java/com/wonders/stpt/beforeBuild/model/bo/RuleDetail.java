package com.wonders.stpt.beforeBuild.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_BEFORE_RULE_DETAIL")
public class RuleDetail {
	private String id;
	private String orderNum;
	private String paperName;
	private String paperId;
	private String prePaperName;
	private String prePaperId;
	private String nextPaperName;
	private String nextPaperId;
	private String validDays;
	private String miniDays;
	private String planDays;
	private String remark;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String removed;
	private String oldDeptId;
	private String ruleVersionId;
	private String ifNode;
	private String ifMilestone;
	
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
	
	@Column(name = "ORDER_NUM", length = 100)
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
	
	@Column(name = "PRE_PAPER_NAME", length = 500)
	public String getPrePaperName() {
		return prePaperName;
	}
	public void setPrePaperName(String prePaperName) {
		this.prePaperName = prePaperName;
	}
	
	@Column(name = "PRE_PAPER_ID", length = 500)
	public String getPrePaperId() {
		return prePaperId;
	}
	public void setPrePaperId(String prePaperId) {
		this.prePaperId = prePaperId;
	}
	
	@Column(name = "NEXT_PAPER_NAME", length = 500)
	public String getNextPaperName() {
		return nextPaperName;
	}
	public void setNextPaperName(String nextPaperName) {
		this.nextPaperName = nextPaperName;
	}
	
	@Column(name = "NEXT_PAPER_ID", length = 500)
	public String getNextPaperId() {
		return nextPaperId;
	}
	public void setNextPaperId(String nextPaperId) {
		this.nextPaperId = nextPaperId;
	}
	
	@Column(name = "VALID_DAYS", length = 100)
	public String getValidDays() {
		return validDays;
	}
	public void setValidDays(String validDays) {
		this.validDays = validDays;
	}
	
	@Column(name = "MINI_DAYS", length = 100)
	public String getMiniDays() {
		return miniDays;
	}
	public void setMiniDays(String miniDays) {
		this.miniDays = miniDays;
	}
	
	@Column(name = "PLAN_DAYS", length = 100)
	public String getPlanDays() {
		return planDays;
	}
	public void setPlanDays(String planDays) {
		this.planDays = planDays;
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
	
	@Column(name = "RULE_VERSION_ID", length = 100)
	public String getRuleVersionId() {
		return ruleVersionId;
	}
	public void setRuleVersionId(String ruleVersionId) {
		this.ruleVersionId = ruleVersionId;
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
	
	
}
