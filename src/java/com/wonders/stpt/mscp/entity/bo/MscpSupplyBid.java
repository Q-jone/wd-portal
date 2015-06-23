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
 * MscpSupplyBidʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-12-3
 * @author modify by $Author$
 * @since 1.0
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MSCP_SUPPLY_BID")
public class MscpSupplyBid implements Serializable, BusinessObject {

	private String id; // id

	private String bidCount; // bidCount

	private String bidSum; // bidSum

	private String operatePerson; // operatePerson

	private String operateTime; // operateTime

	private String removed; // removed

	private String statDate; // statDate

	private String supplyCode; // supplyCode

	private String supplyName; // supplyName

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setBidCount(String bidCount) {
		this.bidCount = bidCount;
	}

	@Column(name = "BID_COUNT", nullable = true, length = 20)
	public String getBidCount() {
		return bidCount;
	}

	public void setBidSum(String bidSum) {
		this.bidSum = bidSum;
	}

	@Column(name = "BID_SUM", nullable = true, length = 20)
	public String getBidSum() {
		return bidSum;
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

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	@Column(name = "SUPPLY_CODE", nullable = true, length = 200)
	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@Column(name = "SUPPLY_NAME", nullable = false, length = 200)
	public String getSupplyName() {
		return supplyName;
	}

}
