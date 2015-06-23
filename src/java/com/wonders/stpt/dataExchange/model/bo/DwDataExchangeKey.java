package com.wonders.stpt.dataExchange.model.bo;

import java.sql.Timestamp;
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
 * DwDataExchangeStore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DW_DATA_EXCHANGE_KEY")
public class DwDataExchangeKey implements java.io.Serializable {

	// Fields

	private String id;
	private String keyname;
	private String keyvalue;
	private String type;	
	private Date createTime;
	private Date operateTime;	
	private Integer removed;
	// Constructors

	/** default constructor */
	public DwDataExchangeKey() {
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERATETIME", length = 7)
	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}


	@Column(name = "KEYNAME", length = 100)
	public String getKeyname() {
		return keyname;
	}


	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}


	@Column(name = "KEYVALUE", length = 200)
	public String getKeyvalue() {
		return keyvalue;
	}


	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}


	@Column(name = "TYPE", length = 50)
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Column(name = "REMOVED")
	public Integer getRemoved() {
		return removed;
	}


	public void setRemoved(Integer removed) {
		this.removed = removed;
	}

	
}