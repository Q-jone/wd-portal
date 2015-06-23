package com.wonders.stpt.organTree.entity.vo;

public class NodeVo {
	private Long pid;
	private Long id;
	private String name;
	private Long orders;
	private Long levels;
	private String description;
	private boolean isParent;
	private String typeId;
	private String icon;
	private boolean doCheck;
	private boolean chkDisabled;
	
	
	

	public boolean isDoCheck() {
		return doCheck;
	}


	public void setDoCheck(boolean doCheck) {
		this.doCheck = doCheck;
	}


	public boolean isChkDisabled() {
		return chkDisabled;
	}


	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}


	public String getTypeId() {
		return typeId;
	}


	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public NodeVo(){
		this.isParent = true;
	}
	
	
	public boolean isIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getOrders() {
		return orders;
	}
	public void setOrders(Long orders) {
		this.orders = orders;
	}
	public Long getLevels() {
		return levels;
	}
	public void setLevels(Long levels) {
		this.levels = levels;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
