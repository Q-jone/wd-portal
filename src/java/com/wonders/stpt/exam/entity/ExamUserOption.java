package com.wonders.stpt.exam.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "T_EXAM_UOPTION")
public class ExamUserOption  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3268074741382211492L;

	private String id; //

	private String mainId; //

	private String groupId; //

	private String questId; //

	private String optionId; //

	private String remark; //

	private long removed; //

	private String cTime; // 填写时间

	private String loginName; //

	private String deptId; //

	private String answer; // 如果题目是简答题，则保存录入的答案

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

	//
	@Column(name = "MAIN_ID", nullable = true, length = 50)
	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	//
	@Column(name = "GROUP_ID", nullable = true, length = 50)
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	//
	@Column(name = "QUEST_ID", nullable = true, length = 50)
	public String getQuestId() {
		return questId;
	}

	public void setQuestId(String questId) {
		this.questId = questId;
	}

	//
	@Column(name = "OPTION_ID", nullable = true, length = 50)
	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	//
	@Column(name = "REMARK", nullable = true, length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	//
	@Column(name = "REMOVED", nullable = true, length = 9)
	public long getRemoved() {
		return removed;
	}

	public void setRemoved(long removed) {
		this.removed = removed;
	}

	// 填写时间
	@Column(name = "C_TIME", nullable = true, length = 19)
	public String getCTime() {
		return cTime;
	}

	public void setCTime(String cTime) {
		this.cTime = cTime;
	}

	//
	@Column(name = "LOGIN_NAME", nullable = true, length = 50)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	//
	@Column(name = "DEPT_ID", nullable = true, length = 50)
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	// 如果题目是简答题，则保存录入的答案
	@Column(name = "ANSWER", nullable = true, length = 1000)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
