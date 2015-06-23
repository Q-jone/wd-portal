/**
 * 
 */
package com.wonders.stpt.processInfo.model;

/** 
 * @ClassName: MsgCountVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-7-30 上午10:19:15 
 *  
 */
public class MsgCountVo {
	private int readCount;
	private int unReadCount;
	private String incidentNo;
	private String modelName;
	private int order;
	
	public MsgCountVo(){
		this.readCount = 0;
		this.unReadCount = 0;
	}
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getUnReadCount() {
		return unReadCount;
	}
	public void setUnReadCount(int unReadCount) {
		this.unReadCount = unReadCount;
	}
	public String getIncidentNo() {
		return incidentNo;
	}
	public void setIncidentNo(String incidentNo) {
		this.incidentNo = incidentNo;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
}
