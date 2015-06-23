package com.wonders.stpt.delayWarn.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_DELAY_ITEM")
public class DelayItem {
	private String id;
	private String pname;
	private String pincident;
	private String cname;
	private String cincident;
	private String overDate;
	private String delayDate;
	private String delayPerson;
	private String delayTimes;
	private String updateTime;
	private String removed;
	private String title;
	private String dept;
	private String taskid;
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
	
	@Column(name = "PNAME")
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	@Column(name = "PINCIDENT")
	public String getPincident() {
		return pincident;
	}
	public void setPincident(String pincident) {
		this.pincident = pincident;
	}
	
	@Column(name = "CNAME")
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@Column(name = "CINCIDENT")
	public String getCincident() {
		return cincident;
	}
	public void setCincident(String cincident) {
		this.cincident = cincident;
	}
	
	@Column(name = "OVER_DATE")
	public String getOverDate() {
		return overDate;
	}
	public void setOverDate(String overDate) {
		this.overDate = overDate;
	}
	
	@Column(name = "DELAY_DATE")
	public String getDelayDate() {
		return delayDate;
	}
	public void setDelayDate(String delayDate) {
		this.delayDate = delayDate;
	}
	
	@Column(name = "DELAY_PERSON")
	public String getDelayPerson() {
		return delayPerson;
	}
	public void setDelayPerson(String delayPerson) {
		this.delayPerson = delayPerson;
	}
	
	@Column(name = "DELAY_TIMES")
	public String getDelayTimes() {
		return delayTimes;
	}
	public void setDelayTimes(String delayTimes) {
		this.delayTimes = delayTimes;
	}
	
	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "REMOVED")
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "DEPT")
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	@Column(name = "TASKID")
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	
	
}
