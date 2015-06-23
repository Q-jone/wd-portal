package com.wonders.stpt.operation.entity.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "OP_DICTIONARY", schema="STPT")
public class OpDictionary {

	private static final long serialVersionUID = 5829916746871193979L;
	private String id; 
	private String name;
	
	private int enabled = 1;
	
	private String code;
	
	private int ordernum;
	
	private String typecode;
	
	private String parentid;
	
	private String description;
	
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

	@Column(name = "enabled", nullable = true, length = 1)
	public int getEnabled() {
		return this.enabled;
	}

	@Column(name = "name", nullable = true, length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", nullable = true, length = 200)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "ordernum", nullable = true, length = 11)
	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	@Column(name = "typecode", nullable = true, length = 200)
	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	@Column(name = "description", nullable = true, length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Column(name = "parent_id", nullable = true, length = 40)
	public String getParentid() {
		return parentid;
	}
	
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	
	
}
