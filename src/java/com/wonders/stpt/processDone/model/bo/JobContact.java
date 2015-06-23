package com.wonders.stpt.processDone.model.bo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BasicData")  
@XmlAccessorType(XmlAccessType.FIELD)
public class JobContact {

	private String id;//序号
	private String serial;//工作单编号
	private String main_unit;//主送单位
	private String main_unit_id;//主送单位ID
	private String main_receive_time;//主送单位接受时间
	private String copy_unit;//抄送单位
	private String copy_unit_id;//抄送单位
	private String copy_receive_time;//抄送单位接收时间
	private String contact_date;//联系时间
	private String reply_date;//要求回复时间
	private String time_diff;//时间差
	private String theme;//主题
	private String content;//内容概述
	private String content_attachment_id;//内容附件编号
	//private String content_attachment_id2;//内容附件编号（独有）
	private String content_operator;//经办人
	private String content_signer;//签发人
	private String content_inscribe;//落款
	private String reply_signer;//回复签发人
	private String reply_inscribe;//回复落款
	private String processname;//流程名称
	private String instanceID;//流程实例号
	private String apply_deptId;//发起部门名称
	private String turtleneck;//套头名称
	private String link;//套头名称
	private String officeValidate;
	private String messageUrl;
	private String copyUnitUsers;
	private String arApply;
	private String groupAttribute;
	private String modified;
	private String flag;//归档标志，0-表示未归档，1-表示预归档，2-表示已归档
	private String removed;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getMain_unit() {
		return main_unit;
	}
	public void setMain_unit(String main_unit) {
		this.main_unit = main_unit;
	}
	public String getMain_unit_id() {
		return main_unit_id;
	}
	public void setMain_unit_id(String main_unit_id) {
		this.main_unit_id = main_unit_id;
	}
	public String getMain_receive_time() {
		return main_receive_time;
	}
	public void setMain_receive_time(String main_receive_time) {
		this.main_receive_time = main_receive_time;
	}
	public String getCopy_unit() {
		return copy_unit;
	}
	public void setCopy_unit(String copy_unit) {
		this.copy_unit = copy_unit;
	}
	public String getCopy_unit_id() {
		return copy_unit_id;
	}
	public void setCopy_unit_id(String copy_unit_id) {
		this.copy_unit_id = copy_unit_id;
	}
	public String getCopy_receive_time() {
		return copy_receive_time;
	}
	public void setCopy_receive_time(String copy_receive_time) {
		this.copy_receive_time = copy_receive_time;
	}
	public String getContact_date() {
		return contact_date;
	}
	public void setContact_date(String contact_date) {
		this.contact_date = contact_date;
	}
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
	public String getTime_diff() {
		return time_diff;
	}
	public void setTime_diff(String time_diff) {
		this.time_diff = time_diff;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent_attachment_id() {
		return content_attachment_id;
	}
	public void setContent_attachment_id(String content_attachment_id) {
		this.content_attachment_id = content_attachment_id;
	}
	public String getContent_operator() {
		return content_operator;
	}
	public void setContent_operator(String content_operator) {
		this.content_operator = content_operator;
	}
	public String getContent_signer() {
		return content_signer;
	}
	public void setContent_signer(String content_signer) {
		this.content_signer = content_signer;
	}
	public String getContent_inscribe() {
		return content_inscribe;
	}
	public void setContent_inscribe(String content_inscribe) {
		this.content_inscribe = content_inscribe;
	}
	public String getReply_signer() {
		return reply_signer;
	}
	public void setReply_signer(String reply_signer) {
		this.reply_signer = reply_signer;
	}
	public String getReply_inscribe() {
		return reply_inscribe;
	}
	public void setReply_inscribe(String reply_inscribe) {
		this.reply_inscribe = reply_inscribe;
	}
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	public String getApply_deptId() {
		return apply_deptId;
	}
	public void setApply_deptId(String apply_deptId) {
		this.apply_deptId = apply_deptId;
	}
	public String getTurtleneck() {
		return turtleneck;
	}
	public void setTurtleneck(String turtleneck) {
		this.turtleneck = turtleneck;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getOfficeValidate() {
		return officeValidate;
	}
	public void setOfficeValidate(String officeValidate) {
		this.officeValidate = officeValidate;
	}
	public String getMessageUrl() {
		return messageUrl;
	}
	public void setMessageUrl(String messageUrl) {
		this.messageUrl = messageUrl;
	}
	public String getCopyUnitUsers() {
		return copyUnitUsers;
	}
	public void setCopyUnitUsers(String copyUnitUsers) {
		this.copyUnitUsers = copyUnitUsers;
	}
	public String getArApply() {
		return arApply;
	}
	public void setArApply(String arApply) {
		this.arApply = arApply;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getFlag() {
		return flag;
	}
	public String getRemoved() {
		return removed;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	public String getGroupAttribute() {
		return groupAttribute;
	}
	public void setGroupAttribute(String groupAttribute) {
		this.groupAttribute = groupAttribute;
	}

}
