package com.wonders.stpt.dataExchange.model.vo;

import java.sql.Timestamp;

public class DwDataExchangeStoreGreataSendVo {
	private String id;
	private String dataDate;
	private String dataType;
	private String dataFormat;	
	private String storePath;	
	private String content;
	private Timestamp createTime;
	private Timestamp operateTime;	
	private Long valid;
	
	private String title;
	private String swid;
	private String filezh;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSwid() {
		return swid;
	}
	public void setSwid(String swid) {
		this.swid = swid;
	}
	public String getFilezh() {
		return filezh;
	}
	public void setFilezh(String filezh) {
		this.filezh = filezh;
	}
	
	
}
