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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * TuserRelation实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-19
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_USER_RELATION")
public class TuserRelation implements Serializable, BusinessObject {
	private String id ;
	private Long cid; // id

	private Long tid; // tId

	private String removed; // removed

	private long orders;
	
	private String mainDept;
	
	private String agent;
	
	//private Tuser tuser;
	
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

	@Column(name = "MAIN_DEPT" ,length = 1)
	public String getMainDept() {
		return mainDept;
	}
	
	public void setMainDept(String mainDept) {
		this.mainDept = mainDept;
	}

	
	public void setCid(Long cid) {
		this.cid = cid;
	}

	@Column(name = "C_ID" ,precision=19)
	public Long getCid() {
		return cid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	@Column(name = "T_ID" ,precision=19)
	public Long getTid() {
		return tid;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = false, length = 1)
	public String getRemoved() {
		return removed;
	}

	@Column(name = "ORDERS" ,precision=10)
	public long getOrders() {
		return orders;
	}

	public void setOrders(long orders) {
		this.orders = orders;
	}

	@Column(name = "AGENT", length = 1)
	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

//	 @ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)  
//	 @JoinColumn(name="T_ID",updatable=false)
//	public Tuser getTuser() {
//		return tuser;
//	}
//
//	public void setTuser(Tuser tuser) {
//		this.tuser = tuser;
//	}
//	


}
