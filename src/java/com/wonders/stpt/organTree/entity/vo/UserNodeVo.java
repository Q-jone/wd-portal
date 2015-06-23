package com.wonders.stpt.organTree.entity.vo;


public class UserNodeVo {
	private Long pid;
	private Long id;
	private String name;
	private Long orders;
	private String loginName;
	private boolean isParent;
	public UserNodeVo(){
		this.isParent = false;
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
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public boolean isIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            if (obj instanceof UserNodeVo) {
                if (((UserNodeVo) obj).getId().equals(this.getId())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
