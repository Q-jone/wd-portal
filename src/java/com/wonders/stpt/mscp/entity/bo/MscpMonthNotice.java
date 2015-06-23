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
 * MscpMonthNoticeʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-12-3
 * @author modify by $Author$
 * @since 1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "MSCP_MONTH_NOTICE")
public class MscpMonthNotice implements Serializable, BusinessObject {

	private String id; // id

	private String bidNoticeNum; // bidNoticeNum

	private String bidResultNum; // bidResultNum

	private String ifbNoticeNum; // ifbNoticeNum

	private String operatePerson; // operatePerson

	private String operateTime; // operateTime

	private String removed; // removed

	private String sourceNoticeNum; // sourceNoticeNum

	private String statDate; // statDate

	private String wabNoticeNum; // wabNoticeNum

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setBidNoticeNum(String bidNoticeNum) {
		this.bidNoticeNum = bidNoticeNum;
	}

	@Column(name = "BID_NOTICE_NUM", nullable = true, length = 20)
	public String getBidNoticeNum() {
		return bidNoticeNum;
	}

	public void setBidResultNum(String bidResultNum) {
		this.bidResultNum = bidResultNum;
	}

	@Column(name = "BID_RESULT_NUM", nullable = true, length = 20)
	public String getBidResultNum() {
		return bidResultNum;
	}

	public void setIfbNoticeNum(String ifbNoticeNum) {
		this.ifbNoticeNum = ifbNoticeNum;
	}

	@Column(name = "IFB_NOTICE_NUM", nullable = true, length = 20)
	public String getIfbNoticeNum() {
		return ifbNoticeNum;
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

	public void setSourceNoticeNum(String sourceNoticeNum) {
		this.sourceNoticeNum = sourceNoticeNum;
	}

	@Column(name = "SOURCE_NOTICE_NUM", nullable = true, length = 20)
	public String getSourceNoticeNum() {
		return sourceNoticeNum;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	@Column(name = "STAT_DATE", nullable = false, length = 20)
	public String getStatDate() {
		return statDate;
	}

	public void setWabNoticeNum(String wabNoticeNum) {
		this.wabNoticeNum = wabNoticeNum;
	}

	@Column(name = "WAB_NOTICE_NUM", nullable = true, length = 20)
	public String getWabNoticeNum() {
		return wabNoticeNum;
	}

}
