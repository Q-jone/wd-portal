package com.wonders.stpt.attach.service;

import java.io.File;
import java.util.List;

import com.wonders.stpt.attach.model.bo.AttachFile;
import com.wonders.stpt.attach.model.vo.UploadFile;

public interface FjshService {
	
	public String uploadNewFiles(String fileGroupCode,File[] files,String[] fileNames,String uploader,String uploaderLoginName,String uploadTime,String memo);
	
	public void uploadOverrideFiles(String groupId,File[] files,String[] fileNames,String uploader,String uploaderLoginName,String uploadTime,String memo);
	
	public int getFileNumByDate(String dateStr);
	
	@SuppressWarnings("unchecked")
	public List findFilesByGroupId(String groupId);
	
	@SuppressWarnings("unchecked")
	public List loadFilesByGroupId(String groupId);
	
	@SuppressWarnings("unchecked")
	public List loadOldFilesByGroupId(String groupId);
	
	public void deleteFileById(String id);
	
	public UploadFile loadFileById(String id);
	
	public String flushFileByGroupId(String groupId);
	
	public String getCurrentFileVersion(String groupId,String fileAllName);
	
	@SuppressWarnings("unchecked")
	public List findVersionFilesByGroupId(String groupId,String fileId); 
	
	String copyLocalFiles(String fileGroupId, Boolean isNewestVerson, String uploaderLoginName,String uploader);
	List<AttachFile> getLocalFiles(String fileGroupId, Boolean isNewestVerson);

}
