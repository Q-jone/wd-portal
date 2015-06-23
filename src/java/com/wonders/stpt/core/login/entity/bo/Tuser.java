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
import java.util.HashSet;
import java.util.List;
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
 * TCsUser实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-19
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_USER")
public class Tuser implements Serializable, BusinessObject {

	private Long id; // id

	private String loginName; // loginName

	private String name; // name

	private String operateTime; // operateTime

	private String operator; // operator

	private String password; // password
	
	private String rank; // rank

	private String removed; // removed
	
	private String flag;
	
	private String trackStatus;

	private List<TuserRelation> tuserRelation;
	
	public void setId(Long id) {
		this.id = id;
	}

	@Id  
	@SequenceGenerator(name="t_seq", sequenceName="seq_t_user") 
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="t_seq")  
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/*Transient
	Transient用来注释entity的属性，指定的这些属性不会被持久化，也不会为这些属性建表。								    
	@Transient
	private String name;*/
	
	@Column(name = "LOGIN_NAME", nullable = false, length = 100)
	public String getLoginName() {
		return loginName;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = true, length = 100)
	public String getName() {
		return name;
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

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PASSWORD", nullable = true, length = 100)
	public String getPassword() {
		return password;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	 @OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)  
	 @JoinColumn(name="T_ID",updatable=false)
	 @OrderBy("orders asc")
	 //orders 为数据表的列名
	public List<TuserRelation> getTuserRelation() {
		return tuserRelation;
	}

	public void setTuserRelation(List<TuserRelation> tuserRelation) {
		this.tuserRelation = tuserRelation;
	}

	@Column(name = "RANK", nullable = true, length = 50)
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	@Column(name = "FLAG", nullable = true, length = 1)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

    @Column(name = "TRACK_STATUS", nullable = true, length = 1)
    public String getTrackStatus() {
        return trackStatus;
    }

    public void setTrackStatus(String trackStatus) {
        this.trackStatus = trackStatus;
    }
}
