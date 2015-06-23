package com.wonders.stpt.standardWork.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "STANDARD_WORK_TYPE")
public class StandardWorkType {
	private String id;
	private String typeName;
	private String parentId;
	private String parentTypeName;
	private int nodeOrder; 
	private int nodeLevel;
	private String removed;
	
	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid") 
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "TYPE_NAME", length = 100)
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Column(name = "PARENT_ID", length = 32)
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "PARENT_TYPE_NAME", length = 100)
	public String getParentTypeName() {
		return parentTypeName;
	}
	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}
	
	@Column(name = "NODE_ORDER")
	public int getNodeOrder() {
		return nodeOrder;
	}
	public void setNodeOrder(int nodeOrder) {
		this.nodeOrder = nodeOrder;
	}
	
	@Column(name = "NODE_LEVEL")
	public int getNodeLevel() {
		return nodeLevel;
	}
	public void setNodeLevel(int nodeLevel) {
		this.nodeLevel = nodeLevel;
	}
	
	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	
}
