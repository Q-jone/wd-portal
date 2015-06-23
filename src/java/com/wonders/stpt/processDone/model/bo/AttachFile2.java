package com.wonders.stpt.processDone.model.bo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.BaseBO;

@XmlRootElement(name="AttachFile")  
@XmlAccessorType(XmlAccessType.FIELD)
public class AttachFile2 extends BaseBO{
	
	private long id;	//id
	
	/**
	 * 文件主名
	 */
	private String fileName;
	
	/**
	 * 文件扩展名
	 */
	private String fileExtName;
	
	/**
	 * 文件物理路径
	 */
	private String path;
	
	/**
	 * 文件大小
	 */
	private long fileSize;
	
	/**
	 * 上传人
	 */
	private String uploader;
	
	/**
	 * 上传人帐户
	 */
	private String uploaderLoginName;
	
	/**
	 * 上传时间
	 */
	private String uploadDate;
	
	/**
	 * 文件组编号
	 */
	private String groupId;
	
	/**
	 * 所属应用名
	 */
	private String appName;
	
	/**
	 * 改名后的保存文件名
	 */
	private String saveFileName;
	
	/**
	 * 文件类型
	 */
	private String fileType;
	
	/**
	 * 备注
	 */
	private String memo;
	
	/**
	 * 版本
	 */
	private String version;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 文件组名
	 */
	private String groupName;
	
	public String getFileExtName() {
		return fileExtName;
	}

	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getUploaderLoginName() {
		return uploaderLoginName;
	}

	public void setUploaderLoginName(String uploaderLoginName) {
		this.uploaderLoginName = uploaderLoginName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
