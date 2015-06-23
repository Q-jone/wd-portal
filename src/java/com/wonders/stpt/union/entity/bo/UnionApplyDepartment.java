package com.wonders.stpt.union.entity.bo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wonders.stpt.operation.entity.bo.OpDictionary;

@Entity
@Table(name = "U_APPLICANT_DEPARTMENT")
public class UnionApplyDepartment implements Serializable{

    private String id;
	/**
	 * 单位主键
	 */
	private String deptId;
	/**
	 * 单位名称
	 */
	private String deptName;
	/**
	 * 奖项主键
	 */
	private String prizeId;
	private UnionPrize prize;
	
	private Short quantity = 0;
    
    private long removed= 0l;
	private String cUser;//         VARCHAR2(20), 发起人
	private String cTime;//         VARCHAR2(19), 发起时间
	private String uUser;//         VARCHAR2(20), 更新人
	private String uTime;//         VARCHAR2(19), 更新时间
    
	
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
	@Column(name = "DEPT_ID", nullable = true, length = 40)
    public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	@Column(name = "DEPT_NAME", nullable = true, length = 200)
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	
	@Column(name = "removed", nullable = true, length = 1)
	public long getRemoved() {
		return removed;
	}

	public void setRemoved(long removed) {
		this.removed = removed;
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
	
	@Column(name = "QUANTITY", nullable = true, length = 3)
	public Short getQuantity() {
		return quantity;
	}
	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}
}
