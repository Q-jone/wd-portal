package com.wonders.stpt.union.entity.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "U_APPROVAL")
public class UnionApproval {
    public static final int ORG_STAGE = 1; // 组织参赛
    public static final int APPLY_STAGE = 2; // 申报
    public static final int APPROVE_STAGE = 3; // 汇总审核
    
	private String id;
	private String choice;
	private String remark;
	private String matchId;
	private String checkRole;
	private String checkUser;
	private String checkUserId;
	private String checkTime;
	private String removed;
	private String nodeName;
	private int stage;  //1：组织 2；申报
	private int step;
	private String applyId; 
	
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
	
	@Column(name = "REMARK", length = 4000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "CHOICE", length = 1)
	public String getChoice() {
		return choice;
	}
	
	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	@Column(name = "MATCH_ID", length = 32)
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	@Column(name = "CHECK_ROLE", length = 1)
	public String getCheckRole() {
		return checkRole;
	}
	public void setCheckRole(String checkRole) {
		this.checkRole = checkRole;
	}
	
	@Column(name = "CHECK_USER", length = 50)
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	
	@Column(name = "CHECK_TIME", length = 50)
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	
	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	@Column(name = "STAGE", length = 1)
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	
	@Column(name = "NODE_NAME", length = 200)
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	@Column(name = "APPLY_ID", length = 32)
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	@Column(name = "STEP", length = 2)
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	
	@Column(name = "CHECK_USER_ID", length = 50)
	public String getCheckUserId() {
		return checkUserId;
	}
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}
	
}
