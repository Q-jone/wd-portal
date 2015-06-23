package com.wonders.stpt.train.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_TRAIN_DEPT")
public class TrainDept implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6752540394417305701L;

	private String id;

	private String mainId;

	private String deptName; //部门名称
	
	private String deptId; //部门ID

	private int managePlan; //计划管理类

	private int prodPlan; //计划生产类

	private int manageResult; //完成人次管理类

	private int prodResult; //完成人次生产类
	
	private int manageHour; //课时完成人次管理类

	private int prodHour; //课时完成人次生产类
	
	private double finishRate;

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

	@Column(name = "MAIN_ID", nullable = true, length = 40)
	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	@Column(name = "DEPT_NAME", nullable = true, length = 40)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "DEPT_ID", nullable = true, length = 40)
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "MANAGE_PLAN", nullable = true, length = 10)
	public int getManagePlan() {
		return managePlan;
	}

	public void setManagePlan(int managePlan) {
		this.managePlan = managePlan;
	}

	@Column(name = "PROD_PLAN", nullable = true, length = 10)
	public int getProdPlan() {
		return prodPlan;
	}

	public void setProdPlan(int prodPlan) {
		this.prodPlan = prodPlan;
	}

	@Column(name = "MANAGE_RESULT", nullable = true, length = 10)
	public int getManageResult() {
		return manageResult;
	}

	public void setManageResult(int manageResult) {
		this.manageResult = manageResult;
	}

	@Column(name = "PROD_RESULT", nullable = true, length = 10)
	public int getProdResult() {
		return prodResult;
	}

	public void setProdResult(int prodResult) {
		this.prodResult = prodResult;
	}

	@Column(name = "MANAGE_HOUR", nullable = true, length = 10)
	public int getManageHour() {
		return manageHour;
	}

	public void setManageHour(int manageHour) {
		this.manageHour = manageHour;
	}

	@Column(name = "PROD_HOUR", nullable = true, length = 10)
	public int getProdHour() {
		return prodHour;
	}

	public void setProdHour(int prodHour) {
		this.prodHour = prodHour;
	}

	@Column(name = "FINISH_RATE", nullable = true, length = 10)
	public double getFinishRate() {
		return finishRate;
	}

	public void setFinishRate(double finishRate) {
		this.finishRate = finishRate;
	}

}
