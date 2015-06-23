package com.wonders.stpt.doneItem.model.vo;

public class DoneInfo {
	private String id;
	private String type;
	private String pname;
	private String pincident;
	private String summary;
	private String stepname;
	private String processstatus;
	private String trackstatus;
	private String operatetime;
    private String donetime;
	private int orders;
	private String taskid;
	
	
	
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public String getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPincident() {
		return pincident;
	}
	public void setPincident(String pincident) {
		this.pincident = pincident;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getStepname() {
		return stepname;
	}
	public void setStepname(String stepname) {
		this.stepname = stepname;
	}
	public String getProcessstatus() {
		return processstatus;
	}
	public void setProcessstatus(String processstatus) {
		this.processstatus = processstatus;
	}
	public String getTrackstatus() {
		return trackstatus;
	}
	public void setTrackstatus(String trackstatus) {
		this.trackstatus = trackstatus;
	}

    public String getDonetime() {
        return donetime;
    }

    public void setDonetime(String donetime) {
        this.donetime = donetime;
    }
}
