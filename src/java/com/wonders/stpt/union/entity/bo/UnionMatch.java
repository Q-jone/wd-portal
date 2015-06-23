package com.wonders.stpt.union.entity.bo;

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

import org.hibernate.annotations.GenericGenerator;

import com.sun.xml.internal.ws.api.message.Attachment;

/**
 * Created by Administrator on 2014/8/22.
 */
@Entity
@Table(name = "U_MATCH")
public class UnionMatch implements Serializable {

    public static final int MATCH_NEW_STATUS = 0; // 一级经办人-新建
    public static final int MATCH_REVIEW_STATUS = 1; // 一级领导-审批
    
    public static final int PRIZE_SET_STATUS = 2; // 二级经办人-进行奖项设置
    public static final int PRIZE_SET_DEPT_REVIEW_STATUS = 3; // 二级领导-奖项设置内部审核
    public static final int PRIZE_SET_FIRST_REVIEW_STATUS = 4; // 一级经办人-奖项设置初审
    public static final int PRIZE_SET_APPROVE_STATUS = 5; // 一级经办人-奖项设置审批
    public static final int PRIZE_SET_SECOND_REVIEW_STATUS = 6; // 二级经办人-奖项设置二审
    
    public static final int PRIZE_ASSIGN_STATUS = 7; // 二级经办人-名额分配
    public static final int PRIZE_ASSIGN_OPERATOR_STATUS = 8; // 二级经办人-设置三级申报人
    public static final int PRIZE_ASSIGN_DEPT_REVIEW_STATUS = 9; // 二级领导-名额分配内部审核
    public static final int PRIZE_ASSIGN_FIRST_REVIEW_STATUS = 10; // 一级经办人-名额分配初审
    public static final int PRIZE_ASSIGN_APPROVE_STATUS = 11; // 一级领导-名额分配审批
    
    public static final int MODIFY_STATUS = 99; // 一级经办人-返回修改
    
    public static final int APPLY_STATUS = 20; // 三级经办人-申报（下发）
    
    public static final int ASSESS_SUM_STATUS = 21; // 二级经办人-考评小组预审
    public static final int ASSESS_SUM_REVIEW_STATUS = 22; // 二级领导-考评小组领导审核
    public static final int LEAD_SUM_STATUS = 23; // 一级经办人-领导小组预审
/*    public static final int LEAD_SUM_REVIEW_STATUS = 24; // 一级领导-领导小组领导审批
    public static final int LEAD_SUM_APPROVE_STATUS = 25; // 集团领导-领导小组终审
*/    public static final int PASS_STATUS = 26; // 通过
    
    
    
    public static final String MATCH_TYPE_PERSONAL = "1";
    public static final String MATCH_TYPE_TEAM = "2";
    public static final String MATCH_TYPE_PROJECT = "3";
    public static final String MATCH_TYPE_PROJECT_RESULT = "4";

    private String id;
    private String matchName;
    private String matchType;
    private String beginDate;
    private String endDate;
    private String deptId;
    private String deptName;
    private String operatorId;
    private String operator;    
    private String attach;   
    private int status = 0;
    private String themeId;
    private UnionTheme theme;
    
    private String todoId;
    private String handlerId;
    private String handlerName;
    
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

	@Column(name = "MATCH_NAME", nullable = true, length = 200)
	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	@Column(name = "MATCH_TYPE", nullable = true, length = 100)
	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	@Column(name = "BEGIN_DATE", nullable = true, length = 40)
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "END_DATE", nullable = true, length = 40)
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	@Column(name = "OPERATOR_ID", nullable = true, length = 40)
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "OPERATOR", nullable = true, length = 100)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "STATUS", nullable = true, length = 1)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "THEME_ID", nullable = true, length = 40)
	public String getThemeId() {
		return themeId;
	}

	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn(name="THEME_ID" ,nullable = true,insertable=false,updatable=false)
	public UnionTheme getTheme() {
		return theme;
	}
	public void setTheme(UnionTheme theme) {
		this.theme = theme;
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
	@Column(name = "ATTACH", nullable = true, length = 40)
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
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
