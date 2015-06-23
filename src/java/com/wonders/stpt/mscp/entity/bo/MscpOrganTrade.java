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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * MscpOrganTradeʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-12-3
 * @author modify by $Author$
 * @since 1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "MSCP_ORGAN_TRADE")
public class MscpOrganTrade implements Serializable, BusinessObject {

	private String id; // id

	private String operatePerson; // operatePerson

	private String operateTime; // operateTime

	private String organCode; // organCode

	private String organName; // organName

	private String removed; // removed

	private String statDate; // statDate

	private String tradeCount; // tradeCount

	private String tradeSum; // tradeSum

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	@Column(name = "OPERATE_PERSON", nullable = true, length = 50)
	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 50)
	public String getOperateTime() {
		return operateTime;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	@Column(name = "ORGAN_CODE", nullable = false, length = 200)
	public String getOrganCode() {
		return organCode;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	@Column(name = "ORGAN_NAME", nullable = true, length = 200)
	public String getOrganName() {
		return organName;
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

	@Column(name = "STAT_DATE", nullable = false, length = 20)
	public String getStatDate() {
		return statDate;
	}

	public void setTradeCount(String tradeCount) {
		this.tradeCount = tradeCount;
	}

	@Column(name = "TRADE_COUNT", nullable = true, length = 20)
	public String getTradeCount() {
		return tradeCount;
	}

	public void setTradeSum(String tradeSum) {
		this.tradeSum = tradeSum;
	}

	@Column(name = "TRADE_SUM", nullable = true, length = 20)
	public String getTradeSum() {
		return tradeSum;
	}

}
