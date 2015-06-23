package com.wonders.stpt.train.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_TRAIN_MAIN")
public class TrainMain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6752540394417305701L;

	private String id;

	private int type = 0;

	private String name; //类别名称

	private int managePlan; //计划管理类

	private int prodPlan; //计划生产类

	private int manageResult; //完成人次管理类

	private int prodResult; //完成人次生产类
	
	private String year; //年份

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

	@Column(name = "TYPE", nullable = true, length = 1)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	// 名称
	@Column(name = "NAME", nullable = true, length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "YEAR", nullable = true, length = 4)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
