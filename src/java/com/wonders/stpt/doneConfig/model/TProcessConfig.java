package com.wonders.stpt.doneConfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TProcessConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_PROCESS_CONFIG")
public class TProcessConfig implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String removed;
	private String isNew;
	private String typeId;
	// Constructors

	/** default constructor */
	public TProcessConfig() {
		this.removed="0";
		//this.isNew="0";
	}

	/** full constructor */
	public TProcessConfig(String name, String removed, String isNew,String typeId) {
		this.name = name;
		this.removed = removed;
		this.isNew = isNew;
		this.typeId=typeId;
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

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return this.removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "IS_NEW", length = 1)
	public String getIsNew() {
		return this.isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	@Column(name="TYPE_ID", length = 32)
	public String getTypeId() {
		return typeId;
	}
	
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
}