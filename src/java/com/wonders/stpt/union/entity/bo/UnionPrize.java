package com.wonders.stpt.union.entity.bo;

import java.io.Serializable;
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


@Entity
@Table(name = "U_PRIZE")
public class UnionPrize implements Serializable{
	
    public static final String PRIZE_TYPE_PERSON = "1";

    public static final String PRIZE_TYPE_TEAM = "2";

    public static final String PRIZE_TYPE_PROJECT = "3";

    public static final String PRIZE_TYPE_PROJECT_ACHIEVEMENT = "4";

    private String id;
    /**
     * 类型
     */
    private String prizeType;
    private String prizeSubType;
    /**
     * 名称
     */
    private String prizeName;
    /**
     * 数量
     */
    private Short quantity = 0;
    /**
     * 奖金
     */
    private Double bonus;
    /**
     * 竞赛主键
     */
    private String matchId;
    private UnionMatch match;
    private String personRange;
    
    private long removed= 0l;
	private String cUser;//         VARCHAR2(20), 发起人
	private String cTime;//         VARCHAR2(19), 发起时间
	private String uUser;//         VARCHAR2(20), 更新人
	private String uTime;//         VARCHAR2(19), 更新时间
    
	private String applicatDept;
	private List<UnionApplyDepartment> applyDepartmentList;
	
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
	
	@Column(name = "PRIZE_TYPE", nullable = true, length = 1)
    public String getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(String prizeType) {
		this.prizeType = prizeType;
	}
	
	@Column(name = "PRIZE_SUB_TYPE", nullable = true, length = 1)
	public String getPrizeSubType() {
		return prizeSubType;
	}
	public void setPrizeSubType(String prizeSubType) {
		this.prizeSubType = prizeSubType;
	}
	
	@Column(name = "PRIZE_NAME", nullable = true, length = 100)
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	
	@Column(name = "QUANTITY", nullable = true, length = 3)
	public Short getQuantity() {
		return quantity;
	}
	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}
	@Column(name = "BONUS", nullable = true, length = 12)
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	@Column(name = "MATCH_ID", nullable = true, length = 40)
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	@Column(name = "PERSON_RANGE", nullable = true, length = 1)
	public String getPersonRange() {
		return personRange;
	}
	public void setPersonRange(String personRange) {
		this.personRange = personRange;
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
	@Transient
	public List<UnionApplyDepartment> getApplyDepartmentList() {
		return applyDepartmentList;
	}
	public void setApplyDepartmentList(
			List<UnionApplyDepartment> applyDepartmentList) {
		this.applyDepartmentList = applyDepartmentList;
	}
	
	@Column(name = "APPLY_DEPT", nullable = true, length = 40)
	public String getApplicatDept() {
		return applicatDept;
	}
	public void setApplicatDept(String applicatDept) {
		this.applicatDept = applicatDept;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="MATCH_ID" ,nullable = true,insertable=false,updatable=false)
	public UnionMatch getMatch() {
		return match;
	}
	public void setMatch(UnionMatch match) {
		this.match = match;
	}
	
	
	
}
