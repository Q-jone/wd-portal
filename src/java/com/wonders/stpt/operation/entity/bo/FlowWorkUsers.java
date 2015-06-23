package com.wonders.stpt.operation.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "op_flowwork_users", schema="STPT")
public class FlowWorkUsers implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	/**
	 * 处理人id
	 */
	@Column(name="USER_ID")
	private String userId;
	
	/**
	 * 处理人名字
	 */
	@Column(name="USER_NAME",length=50)
	private String userName;
	/**
	 * 操作名称
	 */
	@Column(name="NODE_NAME",length=100)
	private String nodeName;
	/**
	 * 流程类型id
	 */
	@Column(name="FLOW_TYPE_ID")
	private Long flowTypeId;
	/**
	 * 用户顺序号
	 */
	@Column(name="ORDER_INDEX")
	private int orderIndex=1;
	
	/**
	 * 候选人 group code
	 */
	@Column(name="ALT_AUTH_CODE",length=200)
	private String altAuthCode;
	
	/**
	 * 操作列表，用'|'分隔
	 */
	@Column(name="OPERATE",length=200)
	private String operate;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getFlowTypeId() {
		return flowTypeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public void setFlowTypeId(Long flowTypeId) {
		this.flowTypeId = flowTypeId;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getAltAuthCode() {
		return altAuthCode;
	}

	public void setAltAuthCode(String altAuthCode) {
		this.altAuthCode = altAuthCode;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}
	
}
