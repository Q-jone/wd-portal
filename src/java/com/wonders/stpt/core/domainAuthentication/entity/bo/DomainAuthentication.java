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

package com.wonders.stpt.core.domainAuthentication.entity.bo;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * TDomainAuthentication实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-26
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_DOMAIN_AUTHENTICATION")
public class DomainAuthentication implements Serializable, BusinessObject {

	private String id; // id

	private String appName; // appName

	private String appUrl; // appUrl

	private String callBackUrl; // callBackUrl

	private String defaultUrl; // defaultUrl

	private String operateTime; // operateTime

	private String operator; // operator

	private String removed; // removed

	private String secret; // secret

	private String webKey; // webKey

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Column(name = "APP_NAME", nullable = true, length = 50)
	public String getAppName() {
		return appName;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	@Column(name = "APP_URL", nullable = true, length = 200)
	public String getAppUrl() {
		return appUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	@Column(name = "CALL_BACK_URL", nullable = true, length = 200)
	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	@Column(name = "DEFAULT_URL", nullable = true, length = 200)
	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 50)
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 50)
	public String getOperator() {
		return operator;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Column(name = "SECRET", nullable = true, length = 50)
	public String getSecret() {
		return secret;
	}

	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}

	@Column(name = "WEB_KEY", nullable = true, length = 50)
	public String getWebKey() {
		return webKey;
	}

}
