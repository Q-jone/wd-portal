package com.wonders.stpt.exam.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "T_EXAM_OPTION")
public class ExamOption  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5292557894209316039L;

	private String id; //

	private String mainId; //

	private String groupId; //

	private String questId; //

	private String opCode; // 答案项codeA B C D...

	private String opValue; // 选项值

	private long opType; // 0单选1多选2文本

	private long removed; //

	//
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

	// 答案项codeA B C D...
	@Column(name = "OP_CODE", nullable = true, length = 10)
	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	// 选项值
	@Column(name = "OP_VALUE", nullable = true, length = 200)
	public String getOpValue() {
		return opValue;
	}

	public void setOpValue(String opValue) {
		this.opValue = opValue;
	}

	// 0单选1多选2文本
	@Column(name = "OP_TYPE", nullable = true, length = 9)
	public long getOpType() {
		return opType;
	}

	public void setOpType(long opType) {
		this.opType = opType;
	}

	//
	@Column(name = "REMOVED", nullable = true, length = 9)
	public long getRemoved() {
		return removed;
	}

	public void setRemoved(long removed) {
		this.removed = removed;
	}
}
