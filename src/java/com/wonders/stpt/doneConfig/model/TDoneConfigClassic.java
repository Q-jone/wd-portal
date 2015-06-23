package com.wonders.stpt.doneConfig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TDoneConfigClassic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_DONECONFIG_CLASSIC")
public class TDoneConfigClassic implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String refId;
	private String removed;

	// Constructors

	/** default constructor */
	public TDoneConfigClassic() {
		this.removed="0";
	}

	/** full constructor */
	public TDoneConfigClassic(String name, String refId, String removed) {
		this.name = name;
		this.refId = refId;
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

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "REF_ID", length = 100)
	public String getRefId() {
		return this.refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return this.removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

}