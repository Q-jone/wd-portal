/**
 * 
 */
package com.wonders.stpt.processDone.model.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: TodoItem 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-22 下午03:29:06 
 *  
 */

@Entity
@Table(name = "T_PROCESS_DONE")
public class ProcessDone implements Serializable{
 
	private static final long serialVersionUID = 8471458287730081764L;
	// Fields

	private String id;
	private String pid;
	private String pname;
	private String ptype;	
	private String summary;	
	private String data;
	private Date startTime;
	private Date endTime;	
	private Long status;
	private String applyUser;
	private String applyDept;	
	private String doneUsers;	
	private String remark;	
	private String taskid;		
	// Constructors

	/** default constructor */
	public ProcessDone() {
	}


	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PID", length = 50)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Column(name = "PNAME", length = 50)
	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	
	@Column(name = "PTYPE", length = 50)
	public String getPtype() {
		return this.ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	
	@Column(name = "SUMMARY")
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}	
	
	@Lob
    @Basic(fetch=FetchType.EAGER)
    @Column(name = "DATA",columnDefinition = "CLOB")
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME")
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TIME")
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "STATUS")
	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	@Column(name = "APPLY_USER", length = 25)
	public String getApplyUser() {
		return this.applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	
	@Column(name = "APPLY_DEPT", length = 50)
	public String getApplyDept() {
		return this.applyDept;
	}

	public void setApplyDept(String applyDept) {
		this.applyDept = applyDept;
	}
	
	@Column(name = "DONE_USERS")
	public String getDoneUsers() {
		return this.doneUsers;
	}

	public void setDoneUsers(String doneUsers) {
		this.doneUsers = doneUsers;
	}	
	
	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "TASKID")
	public String getTaskid() {
		return this.taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}	
}
	
