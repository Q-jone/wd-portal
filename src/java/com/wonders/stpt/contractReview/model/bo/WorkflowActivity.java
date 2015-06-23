/**
 * 
 */
package com.wonders.stpt.contractReview.model.bo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: WorkflowActivity 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-5-31 下午10:10:33 
 *  
 */

@Entity
@Table(name = "T_WORKFLOW_ACTIVITY")
@XmlRootElement
public class WorkflowActivity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6906949791403793835L;
	//主键
	private String id = "";
	//流程名称
	private String process= "";
	//流程实例号
	private String incident= "";
	//流程摘要
	private String summary= "";
	//步骤名称
	private String step= "";
	//当前步骤操作人
	private String operator= "";
	//数据map json格式
	private String data = "";
	//流程提交时间
	private String operateTime = "";
	//更新时间
	private String updateTime= "";
	//状态位 0未激活 1已激活
	private String status= "";
	//标志位
	private String removed= "";
	//选项代码
	private String optionCode = "";
	
	private String beginTime = "";
	
	private String endTime = "";
	
	public WorkflowActivity(){
		this.status = "0";
		this.updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		this.beginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		this.removed = "0";
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
	 * @return the step
	 */
	@Column(name = "STEP", nullable = true, length = 50)
	public String getStep() {
		return step;
	}

	/**
	 * @param step the step to set
	 */
	public void setStep(String step) {
		this.step = step;
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
	 * @return the data
	 */
	@Lob
    @Basic(fetch=FetchType.EAGER)
	@Column(name = "DATA",columnDefinition = "CLOB")
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
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
	 * @return the operateTime
	 */
	@Column(name = "OPERATE_TIME", nullable = true, length = 50)
	public String getOperateTime() {
		return operateTime;
	}


	/**
	 * @param operateTime the operateTime to set
	 */
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}


	/**
	 * @return the updateTime
	 */
	@Column(name = "UPDATE_TIME", nullable = true, length = 50)
	public String getUpdateTime() {
		return updateTime;
	}


	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


	/**
	 * @return the status
	 */
	@Column(name = "STATUS", nullable = true, length = 1)
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the summary
	 */
	@Column(name = "summary", nullable = true, length = 4000)
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
	 * @return the optionCode
	 */
	@Column(name = "OPTION_CODE", nullable = true, length = 50)
	public String getOptionCode() {
		return optionCode;
	}


	/**
	 * @param optionCode the optionCode to set
	 */
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	@Column(name = "BEGIN_TIME", nullable = true, length = 50)
	public String getBeginTime() {
		return beginTime;
	}


	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "END_TIME", nullable = true, length = 50)
	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}
