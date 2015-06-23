/**
 * 
 */
package com.wonders.stpt.processStop.model.bo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: HtxxStop 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午11:30:53 
 *  
 */

@Entity
@Table(name = "T_PROCESS_STOP")
public class ProcessStop implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1034033453779062153L;
	private String id ;
	private String process;
	private String incident;
	private String summary;
	private String reason;
	private String attach;
	private String operator;
	private String operateTime;
	private String removed;
	private String mainId;
	
	public ProcessStop(){
		this.removed = "0";
		this.operateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the process
	 */
	@Column(name = "PROCESS", nullable = true, length = 50)
	public String getProcess() {
		return process;
	}
	/**
	 * @param process the process to set
	 */
	public void setProcess(String process) {
		this.process = process;
	}
	/**
	 * @return the incident
	 */
	@Column(name = "INCIDENT", nullable = true, length = 50)
	public String getIncident() {
		return incident;
	}
	/**
	 * @param incident the incident to set
	 */
	public void setIncident(String incident) {
		this.incident = incident;
	}
	/**
	 * @return the summary
	 */
	@Column(name = "SUMMARY", nullable = true, length = 2000)
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return the reason
	 */
	@Column(name = "REASON", nullable = true, length = 4000)
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the attach
	 */
	@Column(name = "ATTACH", nullable = true, length = 200)
	public String getAttach() {
		return attach;
	}
	/**
	 * @param attach the attach to set
	 */
	public void setAttach(String attach) {
		this.attach = attach;
	}
	/**
	 * @return the operator
	 */
	@Column(name = "OPERATOR", nullable = true, length = 50)
	public String getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * @return the operatorTime
	 */
	@Column(name = "OPERATE_TIME", nullable = true, length = 50)
	public String getOperateTime() {
		return operateTime;
	}
	/**
	 * @param operatorTime the operatorTime to set
	 */
	
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	/**
	 * @return the removed
	 */
	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}
	/**
	 * @param removed the removed to set
	 */
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	/**
	 * @return the mainId
	 */
	@Column(name = "MAIN_ID", nullable = true, length = 50)
	public String getMainId() {
		return mainId;
	}
	/**
	 * @param mainId the mainId to set
	 */
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	
	
}
