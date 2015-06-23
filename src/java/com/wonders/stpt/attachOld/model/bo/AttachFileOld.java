package com.wonders.stpt.attachOld.model.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;


@Entity
@Table(name="T_ATTACH", schema="STPT")
public class AttachFileOld implements Serializable{
	
	private Long id;	//id
	private String fileName;
	private String fileExtName;
	private String path;
	private Long fileSize;
	private String uploader;
	private String uploaderLoginName;
	private String uploadDate;
	private String groupId;
	private String appName;
	private String saveFileName;
	private String fileType;
	private String memo;
	private String version;
	private String status;
	private String groupName;
	private int removed;
	private String operator;
	//private String keyWord;
	private Long operateTime;

	@Id  
	@SequenceGenerator(name="t_seq_attach", sequenceName="seq_attach",allocationSize=1,initialValue=1) 
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="t_seq_attach")  
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "FILENAME", nullable = true, length = 200)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name = "FILEEXTNAME", nullable = true, length = 20)
	public String getFileExtName() {
		return fileExtName;
	}
	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}
	@Column(name = "PATH", nullable = true, length = 200)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Column(name = "FILESIZE")
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	@Column(name = "UPLOADER", nullable = true, length = 30)
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	@Column(name = "UPLOADER_LOGIN_NAME", nullable = true, length = 50)
	public String getUploaderLoginName() {
		return uploaderLoginName;
	}
	public void setUploaderLoginName(String uploaderLoginName) {
		this.uploaderLoginName = uploaderLoginName;
	}
	@Column(name = "UPLOADDATE", nullable = true, length = 20)
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	@Column(name = "GROUPID", nullable = true, length = 100)
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	@Column(name = "APPNAME", nullable = true, length = 50)
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	@Column(name = "SAVEFILENAME", nullable = true, length = 50)
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	@Column(name = "FILETYPE", nullable = true, length = 20)
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Column(name = "MEMO", nullable = true, length = 200)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name = "VERSION", nullable = true, length = 20)
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Column(name = "STATUS", nullable = true, length = 20)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "GROUPNAME", nullable = true, length = 50)
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name = "REMOVED")
	public int getRemoved() {
		return removed;
	}
	public void setRemoved(int removed) {
		this.removed = removed;
	}
	@Column(name = "OPERATOR", nullable = true, length = 200)
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Column(name = "OPERATE_TIME")
	public Long getOperateTime() {
		if(operateTime==null){
			Date d = new Date();
			operateTime = d.getTime();
		}
		return operateTime;
	}
	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}
	
	
	
}
