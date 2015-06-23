package com.wonders.stpt.exam.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "T_EXAM_QUESTION")
public class ExamQuestion  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4384291099926533671L;

	private String id; //

	private String mainId; // 主表id

	private String groupId; // 题目组id

	private String title; // 题目

	private long questNum; // 题目顺序号
	private long showNum; // 是否显示序号

	private String rightAnswer; // 正确答案

	private long questType; // 题目类型 0单选1多选2文本

	private String score; // 分值

	private String remark; // 备注

	private long removed; //

	private String loginName; //

	private String deptId; //

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

	// 主表id
	@Column(name = "MAIN_ID", nullable = true, length = 50)
	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	// 题目组id
	@Column(name = "GROUP_ID", nullable = true, length = 50)
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	// 题目
	@Column(name = "TITLE", nullable = true, length = 1000)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// 题目顺序号
	@Column(name = "QUEST_NUM", nullable = true, length = 9)
	public long getQuestNum() {
		return questNum;
	}
	
	public void setQuestNum(long questNum) {
		this.questNum = questNum;
	}
	
	
	// 是否显示序号
	@Column(name = "SHOW_NUM", nullable = true, length = 9)
	public long getShowNum() {
		return showNum;
	}

	public void setShowNum(long showNum) {
		this.showNum = showNum;
	}

	// 正确答案
	@Column(name = "RIGHT_ANSWER", nullable = true, length = 1000)
	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	// 题目类型 0单选1多选2文本
	@Column(name = "QUEST_TYPE", nullable = true, length = 9)
	public long getQuestType() {
		return questType;
	}

	public void setQuestType(long questType) {
		this.questType = questType;
	}

	// 分值
	@Column(name = "SCORE", nullable = true, length = 20)
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	// 备注
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

}
