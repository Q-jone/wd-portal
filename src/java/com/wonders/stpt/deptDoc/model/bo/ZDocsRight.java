package com.wonders.stpt.deptDoc.model.bo;

// Generated 2014-10-15 14:49:13 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ZDocsRight generated by hbm2java
 */
@Entity
@Table(name = "Z_DOCS_RIGHT")
public class ZDocsRight implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3733942804676006791L;
	private String sid;
	private String rightid;
	private String empid;
	private String type;
	private String uptuser;
	private Date uptdate;
	private String empname;


	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "SID", unique = true, nullable = false, length = 50)
	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Column(name = "RIGHTID", nullable = false, length = 50)
	public String getRightid() {
		return this.rightid;
	}

	public void setRightid(String rightid) {
		this.rightid = rightid;
	}

	@Column(name = "EMPID", nullable = false, length = 50)
	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	@Column(name = "TYPE", nullable = false, length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "UPTUSER", nullable = false, length = 50)
	public String getUptuser() {
		return this.uptuser;
	}

	public void setUptuser(String uptuser) {
		this.uptuser = uptuser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPTDATE")
	public Date getUptdate() {
		return this.uptdate;
	}

	public void setUptdate(Date uptdate) {
		this.uptdate = uptdate;
	}
	
	@Column(name = "EMPNAME", length = 50)
	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	

}
