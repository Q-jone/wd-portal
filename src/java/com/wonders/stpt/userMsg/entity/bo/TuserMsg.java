package com.wonders.stpt.userMsg.entity.bo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

@Entity
@Table(name = "T_USER_MSG")
public class TuserMsg implements Serializable, BusinessObject{
	private String id;
	private Long rid;
	private Long sid;
	private String rname;
	private String sname;
	private String title;
	private String content;
	private String sendDate;
	private String seeDate;
	private String forwardId;
	private String state;
	private String sendState;
	private String seeState;
	private String attach;
	private String replyId;
	private String sendMode;
	private String operator;
	public TuserMsg(){
		this.state = "0";
	}
	//@GenericGenerator(name = "system-uuid", strategy = "uuid")　声明一个策略通用生成器，name为"system-uuid",策略strategy为"uuid"。
	//@GeneratedValue(generator = "system-uuid")　用generator属性指定要使用的策略生成器。
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
	@Column(name = "RID" ,precision=19)
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	@Column(name = "SID" ,precision=19)
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	@Column(name = "RNAME", nullable = true, length = 50)
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Column(name = "SNAME", nullable = true, length = 50)
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	@Column(name = "SEND_MODE", nullable = true, length = 1)
	public String getSendMode() {
		return sendMode;
	}
	public void setSendMode(String sendMode) {
		this.sendMode = sendMode;
	}
	@Column(name = "FORWARD_ID", nullable = true, length = 50)
	public String getForwardId() {
		return forwardId;
	}
	public void setForwardId(String forwardId) {
		this.forwardId = forwardId;
	}
	@Column(name = "REPLY_ID", nullable = true, length = 50)
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	@Column(name = "TITLE", nullable = true, length = 1000)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Lob
    @Basic(fetch=FetchType.EAGER)
    @Column(name = "CONTENT",columnDefinition = "CLOB")
	//@Column(name = "CONTENT", nullable = true, length = 4000)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "SEND_DATE", nullable = true, length = 19)
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	@Column(name = "SEE_DATE", nullable = true, length = 19)
	public String getSeeDate() {
		return seeDate;
	}
	public void setSeeDate(String seeDate) {
		this.seeDate = seeDate;
	}
	
	@Column(name = "STATE", nullable = true, length = 1)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "SEND_STATE", nullable = true, length = 1)
	public String getSendState() {
		return sendState;
	}
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	@Column(name = "SEE_STATE", nullable = true, length = 1)
	public String getSeeState() {
		return seeState;
	}
	public void setSeeState(String seeState) {
		this.seeState = seeState;
	}
	@Column(name = "ATTACH", nullable = true, length = 50)
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	@Column(name = "OPERATOR", nullable = true, length = 50)
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	
}
