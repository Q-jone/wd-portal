/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.mscp.entity.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * MscpProfileʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-12-3
 * @author modify by $Author$
 * @since 1.0
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MSCP_PROFILE")
public class MscpProfile implements Serializable, BusinessObject {

	private String id; // id

	private String complainNum; // complainNum

	private String expertNum; // expertNum

	private String operatePerson; // operatePerson

	private Date operateTime; // operateTime

	private String qualifySupplyNum; // qualifySupplyNum

	private String regSupplyNum; // regSupplyNum

	private String removed; // removed

	private String statDate; // statDate
	
	private String purchaseNum;

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setComplainNum(String complainNum) {
		this.complainNum = complainNum;
	}

	@Column(name = "COMPLAIN_NUM", nullable = true, length = 20)
	public String getComplainNum() {
		return complainNum;
	}

	public void setExpertNum(String expertNum) {
		this.expertNum = expertNum;
	}

	@Column(name = "EXPERT_NUM", nullable = true, length = 20)
	public String getExpertNum() {
		return expertNum;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	@Column(name = "OPERATE_PERSON", nullable = true, length = 50)
	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 50)
	public Date getOperateTime() {
		return operateTime;
	}

	public void setQualifySupplyNum(String qualifySupplyNum) {
		this.qualifySupplyNum = qualifySupplyNum;
	}

	@Column(name = "QUALIFY_SUPPLY_NUM", nullable = true, length = 20)
	public String getQualifySupplyNum() {
		return qualifySupplyNum;
	}

	public void setRegSupplyNum(String regSupplyNum) {
		this.regSupplyNum = regSupplyNum;
	}

	@Column(name = "REG_SUPPLY_NUM", nullable = true, length = 20)
	public String getRegSupplyNum() {
		return regSupplyNum;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	@Column(name = "STAT_DATE", nullable = true, length = 20)
	public String getStatDate() {
		return statDate;
	}

	@Column(name = "PURCHASE_NUM", nullable = true, length = 20)
	public String getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

}
