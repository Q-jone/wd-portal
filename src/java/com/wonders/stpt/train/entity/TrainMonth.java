package com.wonders.stpt.train.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_TRAIN_MONTH")
public class TrainMonth implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6752540394417305701L;

	private String id;

	private String mainId;

	private int month; //月份

	private int trained; //培训完成人次

	private int tested; //考核人次
	
	private int passed; //考核通过人次

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

	@Column(name = "MONTH", nullable = true, length = 4)
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Column(name = "TRAINED", nullable = true, length = 10)
	public int getTrained() {
		return trained;
	}

	public void setTrained(int trained) {
		this.trained = trained;
	}

	@Column(name = "TESTED", nullable = true, length = 10)
	public int getTested() {
		return tested;
	}

	public void setTested(int tested) {
		this.tested = tested;
	}

	@Column(name = "PASSED", nullable = true, length = 10)
	public int getPassed() {
		return passed;
	}

	public void setPassed(int passed) {
		this.passed = passed;
	}

}
