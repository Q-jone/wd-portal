package com.wonders.stpt.operation.entity.bo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.wonders.stpt.util.DateUtil;

@Entity
@Table(name = "OP_DOC_SEND", schema="STPT")
public class OpDocSend implements Serializable{
		
	private String id; 
	private String fileType;//          VARCHAR2(40),
	private String fileTypeSub;//          VARCHAR2(40),
	private String fileCode;//          VARCHAR2(40),
	
	private String docTitle;//          VARCHAR2(500),
	private String remark;//         VARCHAR2(2000),
	private String contentAtt;//          VARCHAR2(40),
	private String contentAttMain;//          VARCHAR2(40),
	
	private long state = 0l;//      NUMBER default 0,状态位 0进行中1 完成
	private String cUser;//         VARCHAR2(20), 发起人
	private String cTime;//         VARCHAR2(19), 发起时间
	private String uUser;//         VARCHAR2(20), 更新人
	private String uTime;//         VARCHAR2(19), 更新时间
	private String deptId;//         申报部门
	private long removed= 0l;//        NUMBER default 0,
	private long isUrgent= 0l;//        是否应急发文
	
	private String sendMainId;
	private String sendLineId;
	private String sendMainW;
	private String sendLineW;
	private String sendOutW;
	
	private OpDictionary fileTypeDic;
	private OpDictionary fileTypeSubDic;
	
	private String cUserName;//         VARCHAR2(20), 更新人
	private String deptName;//         申报部门
	
	private String flowGroup;
	
	private String sentDepts;
	private int passCnt;
	private String validDate;
	private String passDate;
	private String pubDate;
	private String applyDate;
	private String isExpired;
    private String isLeader;

	private FlowWorkProcess flowWorkProcess;
	
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
	
	@Column(name = "state", nullable = true, length = 1)
	public long getState() {
		return state;
	}
	@Column(name = "FILE_TYPE", nullable = true, length = 40)
	public String getFileType() {
		return fileType;
	}
	@Column(name = "is_urgent", nullable = true, length = 1)
	public long getIsUrgent() {
		return isUrgent;
	}
	public void setIsUrgent(long isUrgent) {
		this.isUrgent = isUrgent;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Column(name = "FILE_TYPE_SUB", nullable = true, length = 40)
	public String getFileTypeSub() {
		return fileTypeSub;
	}
	public void setFileTypeSub(String fileTypeSub) {
		this.fileTypeSub = fileTypeSub;
	}

	@Column(name = "FILE_CODE", nullable = true, length = 40)
	public String getFileCode() {
		return fileCode;
	}
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	@Column(name = "doc_title", nullable = true, length = 500)
	public String getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
	public void setState(long state) {
		this.state = state;
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
	@Column(name = "remark", nullable = true, length = 4000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "removed", nullable = true, length = 1)
	public long getRemoved() {
		return removed;
	}
	public void setRemoved(long removed) {
		this.removed = removed;
	}
	@Column(name = "content_att", nullable = true, length = 40)
	public String getContentAtt() {
		return contentAtt;
	}
	public void setContentAtt(String contentAtt) {
		this.contentAtt = contentAtt;
	}
	@Column(name = "content_att_main", nullable = true, length = 40)
	public String getContentAttMain() {
		return contentAttMain;
	}
	public void setContentAttMain(String contentAttMain) {
		this.contentAttMain = contentAttMain;
	}
	@Column(name = "SEND_LINE_ID", nullable = true, length = 1000)
	public String getSendLineId() {
		return sendLineId;
	}
	public void setSendLineId(String sendLineId) {
		this.sendLineId = sendLineId;
	}

	@Column(name = "SEND_LINE_W", nullable = true, length = 1000)
	public String getSendLineW() {
		return sendLineW;
	}
	public void setSendLineW(String sendLineW) {
		this.sendLineW = sendLineW;
	}
	@Column(name = "SEND_MAIN_ID", nullable = true, length = 1000)
	public String getSendMainId() {
		return sendMainId;
	}
	public void setSendMainId(String sendMainId) {
		this.sendMainId = sendMainId;
	}
	@Column(name = "SEND_MAIN_W", nullable = true, length = 1000)
	public String getSendMainW() {
		return sendMainW;
	}
	public void setSendMainW(String sendMainW) {
		this.sendMainW = sendMainW;
	}
	
	@Column(name = "SEND_OUT_W", nullable = true, length = 100)
	public String getSendOutW() {
		return sendOutW;
	}
	public void setSendOutW(String sendOutW) {
		this.sendOutW = sendOutW;
	}
	@Column(name="FLOW_GROUP",columnDefinition="VARCHAR(200)")
	public String getFlowGroup() {
		return flowGroup;
	}
	public void setFlowGroup(String flowGroup) {
		this.flowGroup = flowGroup;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinColumn(name="FLOW_GROUP", referencedColumnName="FLOW_UID",nullable = true,insertable = false, updatable = false)
	public FlowWorkProcess getFlowWorkProcess() {
		return flowWorkProcess;
	}
	public void setFlowWorkProcess(FlowWorkProcess flowWorkProcess) {
		this.flowWorkProcess = flowWorkProcess;
	}
	@Column(name = "dept_id", nullable = true, length = 40)
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinColumn(name="FILE_TYPE" ,nullable = true,insertable=false,updatable=false)
	public OpDictionary getFileTypeDic() {
		return fileTypeDic;
	}
	public void setFileTypeDic(OpDictionary fileTypeDic) {
		this.fileTypeDic = fileTypeDic;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinColumn(name="FILE_TYPE_SUB" ,nullable = true,insertable=false,updatable=false)
	public OpDictionary getFileTypeSubDic() {
		return fileTypeSubDic;
	}
	public void setFileTypeSubDic(OpDictionary fileTypeSubDic) {
		this.fileTypeSubDic = fileTypeSubDic;
	}
	@Column(name = "c_user_name", nullable = true, length = 40)
	public String getcUserName() {
		return cUserName;
	}
	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}
	@Column(name = "dept_name", nullable = true, length = 40)
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Column(name = "sent_depts", nullable = true, length = 1000)
	public String getSentDepts() {
		return sentDepts;
	}
	public void setSentDepts(String sentDepts) {
		this.sentDepts = sentDepts;
	}
	@Column(name = "valid_date", nullable = true, length = 20)
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	@Column(name = "pass_cnt", nullable = true, length = 4)
	public int getPassCnt() {
		return passCnt;
	}
	public void setPassCnt(int passCnt) {
		this.passCnt = passCnt;
	}
	@Column(name = "pass_date", nullable = true, length = 20)
	public String getPassDate() {
		return passDate;
	}
	public void setPassDate(String passDate) {
		this.passDate = passDate;
	}
	@Column(name = "pub_date", nullable = true, length = 40)
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	@Column(name = "apply_date", nullable = true, length = 20)
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	@Transient
	public String getIsExpired() {
		if(this.validDate != null && !"".equals(validDate)){
			String today = DateUtil.getNowDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d1 = sdf.parse(validDate);
				Date d2 = sdf.parse(today);
				if(d1.before(d2)){
					return "过期";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "使用";
	}

    @Column(name = "IS_LEADER", length = 1)
    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
    }
}
