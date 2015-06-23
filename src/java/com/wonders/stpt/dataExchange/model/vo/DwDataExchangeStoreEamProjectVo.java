package com.wonders.stpt.dataExchange.model.vo;

import java.sql.Timestamp;

public class DwDataExchangeStoreEamProjectVo {
	private String id;
	private String dataDate;
	private String dataType;
	private String dataFormat;	
	private String storePath;	
	private String content;
	private Timestamp createTime;
	private Timestamp operateTime;	
	private Long valid;
	
	private String projectName;
	private String projectClass;	
	private String reportCompany;
	private String reportPerson;	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDataDate() {
		return dataDate;
	}
	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataFormat() {
		return dataFormat;
	}
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	public String getStorePath() {
		return storePath;
	}
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}
	public Long getValid() {
		return valid;
	}
	public void setValid(Long valid) {
		this.valid = valid;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectClass() {
		return projectClass;
	}
	public void setProjectClass(String projectClass) {
		this.projectClass = projectClass;
	}
	public String getReportCompany() {
		return reportCompany;
	}
	public void setReportCompany(String reportCompany) {
		this.reportCompany = reportCompany;
	}
	
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}	
	
	
}
