package com.wonders.stpt.doneConfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TTodoUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_DONECONFIG_USER")
public class TDoneConfigUser implements java.io.Serializable {
	// Fields
	private String id;
	private String loginName;
	private String numbers;
	private String orders;
	private String isTracking;
	private String removed;

	// Constructors

	/** default constructor */
	public TDoneConfigUser() {
	}

	/** full constructor */
	public TDoneConfigUser(String loginName, String numbers, String orders,
			String isTracking, String removed) {
		this.loginName = loginName;
		this.numbers = numbers;
		this.orders = orders;
		this.isTracking = isTracking;
		this.removed = removed;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "LOGIN_NAME", length = 12)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "NUMBERS", length = 16)
	public String getNumbers() {
		return this.numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	@Column(name = "ORDERS", length = 1)
	public String getOrders() {
		return this.orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	@Column(name = "IS_TRACKING", length = 1)
	public String getIsTracking() {
		return this.isTracking;
	}

	public void setIsTracking(String isTracking) {
		this.isTracking = isTracking;
	}

	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return this.removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

}