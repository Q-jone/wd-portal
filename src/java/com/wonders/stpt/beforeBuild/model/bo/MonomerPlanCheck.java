package com.wonders.stpt.beforeBuild.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_BEFORE_MONOMER_PLAN_CHECK")
public class MonomerPlanCheck {
	private String id;
	private String result;
	private String remark;
	private String monomerPlanId;
	private String checkUser;
	private String checkTime;
	private String removed;
	
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
	
	@Column(name = "RESULT", length = 1)
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@Column(name = "REMARK", length = 4000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "MONOMER_PLAN_ID", length = 32)
	public String getMonomerPlanId() {
		return monomerPlanId;
	}
	public void setMonomerPlanId(String monomerPlanId) {
		this.monomerPlanId = monomerPlanId;
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
	
	
}
