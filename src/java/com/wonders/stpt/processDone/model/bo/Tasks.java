package com.wonders.stpt.processDone.model.bo;

public class Tasks {
	private String taskId;
	private String processName;
	private int incident;
	private String stepLabel;
	private int status;
	private String taskUser;
	private java.sql.Date startTime;
	private java.sql.Date endTime;
	
	public String getTaskUser() {
		return taskUser;
	}
	public void setTaskUser(String taskUser) {
		this.taskUser = taskUser;
	}
	public java.sql.Date getStartTime() {
		return startTime;
	}
	public void setStartTime(java.sql.Date startTime) {
		this.startTime = startTime;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public int getIncident() {
		return incident;
	}
	public void setIncident(int incident) {
		this.incident = incident;
	}
	public String getStepLabel() {
		return stepLabel;
	}
	public void setStepLabel(String stepLable) {
		this.stepLabel = stepLable;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public java.sql.Date getEndTime() {
		return endTime;
	}
	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}
	
}
