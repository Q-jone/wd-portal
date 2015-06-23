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

package com.wonders.stpt.core.login.entity.bo;

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
 * TuserToken实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-28
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_USER_TOKEN")
public class TuserToken implements Serializable, BusinessObject {

	private String id; // id

	private String createTime; // createTime

	private String removed; // removed

	private String userId; // userId

	public TuserToken(){
		this.createTime =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
		this.removed = "0";
	}
	
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

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_TIME", nullable = true, length = 50)
	public String getCreateTime() {
		return createTime;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USER_ID", nullable = true, length = 50)
	public String getUserId() {
		return userId;
	}

}
