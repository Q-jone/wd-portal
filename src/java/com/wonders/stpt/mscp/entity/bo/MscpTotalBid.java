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
 * MscpTotalBidʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-12-3
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "MSCP_TOTAL_BID")
@SuppressWarnings("serial")
public class MscpTotalBid implements Serializable, BusinessObject {

	private String id; // id

	private String abortNum; // abortNum

	private String bidType; // bidType

	private String execNum; // execNum

	private String finishNum; // finishNum

	private String operatePerson; // operatePerson

	private String operateTime; // operateTime

	private String removed; // removed

	private String totalNum; // totalNum

	private String wabSum; // wabSum

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setAbortNum(String abortNum) {
		this.abortNum = abortNum;
	}

	@Column(name = "ABORT_NUM", nullable = true, length = 20)
	public String getAbortNum() {
		return abortNum;
	}

	public void setBidType(String bidType) {
		this.bidType = bidType;
	}

	@Column(name = "BID_TYPE", nullable = false, length = 20)
	public String getBidType() {
		return bidType;
	}

	public void setExecNum(String execNum) {
		this.execNum = execNum;
	}

	@Column(name = "EXEC_NUM", nullable = true, length = 20)
	public String getExecNum() {
		return execNum;
	}

	public void setFinishNum(String finishNum) {
		this.finishNum = finishNum;
	}

	@Column(name = "FINISH_NUM", nullable = true, length = 20)
	public String getFinishNum() {
		return finishNum;
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

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	@Column(name = "TOTAL_NUM", nullable = true, length = 20)
	public String getTotalNum() {
		return totalNum;
	}

	public void setWabSum(String wabSum) {
		this.wabSum = wabSum;
	}

	@Column(name = "WAB_SUM", nullable = true, length = 20)
	public String getWabSum() {
		return wabSum;
	}

}
