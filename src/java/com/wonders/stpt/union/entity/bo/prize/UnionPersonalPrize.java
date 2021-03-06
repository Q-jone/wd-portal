package com.wonders.stpt.union.entity.bo.prize;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.wonders.stpt.union.entity.bo.UnionPrize;


@Entity
@Table(name = "U_PRIZE_PERSONAL")
public class UnionPersonalPrize implements Serializable{

    private String id;

	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 生日
	 */
	private String brithday;
	/**
	 * 政治面貌
	 */
	private String political;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 部门
	 */
	private String deptName;
	/**
	 * 单位主键
	 */
	private String deptId;	
	/**
	 * 工作单位
	 */
	private String unitName;	
	/**
	 * 荣誉
	 */
	private String prizeInfo;
	/**
	 * 主要事迹
	 */
	private String introduct;
    
    private long removed= 0l;
    private String rejected= "0";
    private String modified = "0";
	private String cUser;//         VARCHAR2(20), 发起人
	private String cTime;//         VARCHAR2(19), 发起时间
	private String uUser;//         VARCHAR2(20), 更新人
	private String uTime;//         VARCHAR2(19), 更新时间
	private String cUserName;
	
	private String prizeId;
	private UnionPrize prize;
	
	private String applyId;
	
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
	
	@Column(name = "removed", nullable = true, length = 1)
	public long getRemoved() {
		return removed;
	}
	public void setRemoved(long removed) {
		this.removed = removed;
	}
	
	@Column(name = "rejected", nullable = true, length = 1)
	public String getRejected() {
		return rejected;
	}
	public void setRejected(String rejected) {
		this.rejected = rejected;
	}
    
	@Column(name = "c_user", nullable = true, length = 40)
	public String getcUser() {
		return cUser;
	}
	public void setcUser(String cUser) {
		this.cUser = cUser;
	}
	@Column(name = "c_time", nullable = true, length = 40)
	public String getcTime() {
		return cTime;
	}
	public void setcTime(String cTime) {
		this.cTime = cTime;
	}
	@Column(name = "u_user", nullable = true, length = 40)
	public String getuUser() {
		return uUser;
	}
	public void setuUser(String uUser) {
		this.uUser = uUser;
	}
	@Column(name = "u_time", nullable = true, length = 40)
	public String getuTime() {
		return uTime;
	}
	public void setuTime(String uTime) {
		this.uTime = uTime;
	}
	@Column(name = "NAME", nullable = true, length = 200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "GENDER", nullable = true, length = 1)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name = "BRITHDAY", nullable = true, length = 20)
	public String getBrithday() {
		return brithday;
	}
	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}
	@Column(name = "POLITICAL", nullable = true, length = 50)
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	@Column(name = "POSITION", nullable = true, length = 100)
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Column(name = "DEPT_NAME", nullable = true, length = 200)
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Column(name = "DEPT_ID", nullable = true, length = 40)
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	@Column(name = "PRIZE_INFO", nullable = true, length = 2000)
	public String getPrizeInfo() {
		return prizeInfo;
	}
	public void setPrizeInfo(String prizeInfo) {
		this.prizeInfo = prizeInfo;
	}
	@Column(name = "INTRODUCT", nullable = true, length = 2000)
	public String getIntroduct() {
		return introduct;
	}
	public void setIntroduct(String introduct) {
		this.introduct = introduct;
	}
	@Column(name = "PRIZE_ID", nullable = true, length = 40)
	public String getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="PRIZE_ID" ,nullable = true,insertable=false,updatable=false)
	public UnionPrize getPrize() {
		return prize;
	}
	public void setPrize(UnionPrize prize) {
		this.prize = prize;
	}
	@Column(name = "UNIT_NAME", nullable = true, length = 200)
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	@Column(name = "APPLY_ID", nullable = true, length = 40)
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	@Column(name = "c_user_name", nullable = true, length = 40)
	public String getcUserName() {
		return cUserName;
	}
	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}
	
	@Column(name = "modified", nullable = true, length = 1)
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
}
