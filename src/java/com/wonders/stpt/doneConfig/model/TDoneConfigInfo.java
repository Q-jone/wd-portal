package com.wonders.stpt.doneConfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TDoneconfigInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_DONECONFIG_INFO", schema = "STPT")
public class TDoneConfigInfo implements java.io.Serializable {

	// Fields

	private String id;
	/**
	 * 登陆名
	 */
	private String loginName;
	/**
	 * 类型id
	 */
	private String typeId;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 记录id
	 */
	private String recordId;
	/**
	 * 记录名称
	 */
	private String recordName;
	private String removed;
	/**
	 * 是否跟踪
	 */
	private String is_track;
	/**
	 * 排序(根据添加类型的顺序排序)
	 */
	private String orders;

	// Constructors

	/** default constructor */
	public TDoneConfigInfo() {
		this.removed="0";
		this.is_track="1";
	}

	/** full constructor */
	public TDoneConfigInfo(String loginName, String typeId, String typeName,
			String recordId, String recordName, String removed, String orders,String is_track) {
		this.loginName = loginName;
		this.typeId = typeId;
		this.typeName = typeName;
		this.recordId = recordId;
		this.recordName = recordName;
		this.removed = removed;
		this.orders = orders;
		this.is_track=is_track;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "LOGIN_NAME", length = 50)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "TYPE_ID", length = 32)
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Column(name = "TYPE_NAME", length = 100)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "RECORD_ID", length = 32)
	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	@Column(name = "RECORD_NAME", length = 100)
	public String getRecordName() {
		return this.recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return this.removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "ORDERS", length = 1)
	public String getOrders() {
		return this.orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}
	@Column(name = "IS_TRACK", length = 1)
	public String getIs_track() {
		return is_track;
	}
	@Column(name = "ORDERS", length = 1)
	public void setIs_track(String is_track) {
		this.is_track = is_track;
	}
	
}