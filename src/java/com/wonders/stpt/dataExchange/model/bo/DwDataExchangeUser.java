package com.wonders.stpt.dataExchange.model.bo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * DwDataExchangeLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DW_DATA_EXCHANGE_USER")
public class DwDataExchangeUser implements java.io.Serializable {

	// Fields

	private String id;
	private String userName;
	private String password;
	private String portName;
	private String dataType;
	private String company;
	private String appName;
	private String clientIP;
	private Timestamp operateTime;
	private Long removed;

	// Constructors

	/** default constructor */
	public DwDataExchangeUser() {
	}

	/** full constructor */
	public DwDataExchangeUser(String userName, String password, String portName, String dataType,
			String company, String appName,String clientIP,Timestamp operateTime, Long removed) {
		this.userName = userName;
		this.password = password;
		this.portName = portName;
		this.dataType = dataType;		
		this.company = company;
		this.appName = appName;
		this.clientIP = clientIP;		
		this.operateTime = operateTime;
		this.removed = removed;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 40)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USER_NAME", length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "PASSWORD", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PORT_NAME", length = 50)
	public String getPortName() {
		return this.portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}
	
	@Column(name = "DATA_TYPE", length = 20)
	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Column(name = "COMPANY",length = 100)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	@Column(name = "APP_NAME",length = 20)
	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	
	@Column(name = "CLIENT_IP",length = 100)
	public String getClientIP() {
		return this.clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
		
	@Column(name = "OPERATE_TIME", length = 11)
	public Timestamp getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "REMOVED")
	public Long getremoved() {
		return this.removed;
	}

	public void setremoved(Long removed) {
		this.removed = removed;
	}

}