package com.wonders.stpt.beforeBuild.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_BEFORE_PAPER")
public class Paper {
	private String id;
	private String name;
	private String dealUnit;
	private String removed;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "NAME", length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "DEAL_UNIT", length = 100)
	public String getDealUnit() {
		return dealUnit;
	}
	public void setDealUnit(String dealUnit) {
		this.dealUnit = dealUnit;
	}
	
	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	
}
