package com.wonders.stpt.attachOld.service;

import java.io.File;
import java.util.List;

import com.wonders.stpt.attachOld.model.bo.AttachFileOld;
import com.wonders.stpt.attachOld.model.vo.UploadFile;


public interface FjshServiceOld {
	
	public String uploadNewFiles(String fileGroupCode,File[] files,String[] fileNames,String uploader,String uploaderLoginName,String uploadTime,String memo);
	
	public void uploadOverrideFiles(String groupId,File[] files,String[] fileNames,String uploader,String uploaderLoginName,String uploadTime,String memo);
	
	public int getFileNumByDate(String dateStr);
	
	@SuppressWarnings("unchecked")
	public List findFilesByGroupId(String groupId);
	
	@SuppressWarnings("unchecked")
	public List loadFilesByGroupId(String groupId);
	
	public void deleteFileById(String id);
	
	public UploadFile loadFileById(String id);
	
	public String flushFileByGroupId(String groupId);
	
	public String getCurrentFileVersion(String groupId,String fileAllName);
	
	@SuppressWarnings("unchecked")
	public List findVersionFilesByGroupId(String groupId,String fileId); 
	
	String copyLocalFiles(String fileGroupId, Boolean isNewestVerson, String uploaderLoginName,String uploader);
	List<AttachFileOld> getLocalFiles(String fileGroupId, Boolean isNewestVerson);

}
