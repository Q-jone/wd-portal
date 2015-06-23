package com.wonders.stpt.core.userManage.entity.bo;

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


import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


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
public class StptUser implements Serializable, BusinessObject {

	private Long id; // id

	private String loginName; // loginName

	private String name; // name

	private String operateTime; // operateTime

	private String operator; // operator

	private String password; // password
	
	private String rank; // rank

	private String removed; // removed
	
	private String company;
	
	private String dept;
	
	private String sex;
	
	private String phone;
	
	private String mobile1;
	
	private String mobile2;
	
	private String flag;
	
	private String idcard;
	private String birthday;
	private String nation;
	private String birthplace;
	private String political;
	private String degree;
	private String address;
	private String postcode;
	private String grade;
	private String title;
	private String major;
	private String cpostcode;
	private String cphone;
	private String household;
	private String retire;
	private String caddress;
	private String remark;
	
	
	public StptUser(){
		this.flag = "0";
		this.removed = "0";
		this.operateTime =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	//此时主键id的增长是按照hibernate自动处理的方式，而并非数据库中定义的sequence来处理。必须加allocationSize=1,initialValue=1这两项配置才可以解决上述问题。如下： 
	@Id  
	@SequenceGenerator(name="t_seq", sequenceName="seq_t_user",allocationSize=1,initialValue=1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="t_seq")  
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "LOGIN_NAME", nullable = true, length = 100)
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

	@Column(name = "RANK", nullable = true, length = 50)
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	@Column(name = "COMPANY", nullable = true, length = 200)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name = "DEPT", nullable = true, length = 200)
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	@Column(name = "SEX", nullable = true, length = 1)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "PHONE", nullable = true, length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "MOBILE1", nullable = true, length = 50)
	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	@Column(name = "MOBILE2", nullable = true, length = 50)
	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	@Column(name = "FLAG", nullable = true, length = 1)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "IDCARD", nullable = true, length = 50)
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@Column(name = "BIRTHDAY", nullable = true, length = 50)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "NATION", nullable = true, length = 50)
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "POLITICAL", nullable = true, length = 50)
	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	@Column(name = "DEGREE", nullable = true, length = 50)
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "ADDRESS", nullable = true, length = 200)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "POSTCODE", nullable = true, length = 50)
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "GRADE", nullable = true, length = 200)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "TITLE", nullable = true, length = 200)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "MAJOR", nullable = true, length = 200)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	@Column(name = "CPOSTCODE", nullable = true, length = 50)
	public String getCpostcode() {
		return cpostcode;
	}

	public void setCpostcode(String cpostcode) {
		this.cpostcode = cpostcode;
	}

	@Column(name = "CPHONE", nullable = true, length = 50)
	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	@Column(name = "HOUSEHOLD", nullable = true, length = 50)
	public String getHousehold() {
		return household;
	}

	public void setHousehold(String household) {
		this.household = household;
	}

	
	@Column(name = "RETIRE", nullable = true, length = 50)
	public String getRetire() {
		return retire;
	}

	public void setRetire(String retire) {
		this.retire = retire;
	}

	@Column(name = "CADDRESS", nullable = true, length = 200)
	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	@Column(name = "REMARK", nullable = true, length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "BIRTHPLACE", nullable = true, length = 50)
	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	

}
