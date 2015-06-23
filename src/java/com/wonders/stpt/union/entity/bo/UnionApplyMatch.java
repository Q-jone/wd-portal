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
@Table(name = "U_APPLICANT_MATCH")
public class UnionApplyMatch implements Serializable{
	
    public static final int NEW_STATUS = 0; // 新建
    public static final int APPLY_STATUS = 1; // 待申报
    public static final int DEPT_REVIEW_STATUS = 2; // 待申报
    public static final int SUBMITTED_STATUS = 3; // 待申报
    public static final int ASSESS_STATUS = 4; // 考评小组汇总
    public static final int LEAD_SUMMARY_STATUS = 5; // 领导小组汇总
    public static final int LEAD_APPROVE_STATUS = 6; // 领导小组领导审核
    public static final int GROUP_LEADER_APPROVE_STATUS = 7; // 集团领导审核
    public static final int PASS_STATUS = 8; // 通过
    
    public static final int MODIFY_STATUS = 19; // 返回修改
    
    public static final String DEPT_TZZ = "9000"; //集团机关团总支
    public static final String DEPT_TZZ_NAME = "集团机关团总支"; 
    public static final String DEPT_BZHS = "9001"; //标准化室
    public static final String DEPT_BZHS_NAME = "标准化室"; 
    
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
	private String userName;
	private String loginName;
	
	private String matchId;
	
	private int status = 0;
    
    private long removed= 0l;
	private String cUser;//         VARCHAR2(20), 发起人
	private String cTime;//         VARCHAR2(19), 发起时间
	private String uUser;//         VARCHAR2(20), 更新人
	private String uTime;//         VARCHAR2(19), 更新时间
	
	private String todoId;
    private String handlerId;
    private String handlerName;
	
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
	
	@Column(name = "MATCH_ID", nullable = true, length = 40)
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	
	@Column(name = "STATUS", nullable = true, length = 1)
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	@Column(name = "user_name", nullable = true, length = 40)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "login_name", nullable = true, length = 40)
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Column(name = "TODO_ID", nullable = true, length = 40)
	public String getTodoId() {
		return todoId;
	}
	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}
	
	@Column(name = "HANDLER_ID", nullable = true, length = 40)
	public String getHandlerId() {
		return handlerId;
	}

	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}

	@Column(name = "HANDLER_NAME", nullable = true, length = 40)
	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
}
