package com.wonders.stpt.union.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "U_DEAL_USER")
public class UnionDealUser implements Serializable{
	
    private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 登录名
	 */
	private String loginName;
	
	private int status;		//审批状态
	
	private int stage;		//审批阶段（1、竞赛组织，2、竞赛申报，3、竞赛申报审核）
    
	private String dealUrl;  //处理页面
	
	private String ifAddTodo;  //是否进待办
	
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

	@Column(name = "STATUS", nullable = true, length = 1)
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "NAME", nullable = true, length = 32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "LOGIN_NAME", nullable = true, length = 32)
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Column(name = "DEAL_URL", nullable = true, length = 200)
	public String getDealUrl() {
		return dealUrl;
	}
	public void setDealUrl(String dealUrl) {
		this.dealUrl = dealUrl;
	}
	
	@Column(name = "STAGE", nullable = true, length = 1)
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	
	@Column(name = "IF_ADD_TODO", nullable = true, length = 1)
	public String getIfAddTodo() {
		return ifAddTodo;
	}
	public void setIfAddTodo(String ifAddTodo) {
		this.ifAddTodo = ifAddTodo;
	}
	
	

}
