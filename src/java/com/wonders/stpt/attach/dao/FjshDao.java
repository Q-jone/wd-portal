package com.wonders.stpt.attach.dao;

import java.io.File;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.wonders.stpt.attach.model.bo.AttachFile;


public interface FjshDao{
	
	public void saveFileToLocal(File sourceFile,String destFilePath,String destFileName);
	
	public int getFileNumByDate(String dateStr);
	
	@SuppressWarnings("unchecked")
	public List findFilesByGroupId(String groupId);
	
	public void deleteLocalFile(String filePath);
	
	public void versionLocalFile(String filePathStr,String fileAllName,String version);
	public void versionLocalFileRestore(String filePathStr,String fileAllName,String version);
	
	public String getCurrentFileVersion(String groupId,String fileAllName);
	
	@SuppressWarnings("unchecked")
	public List findFilesByHql(String hql);
	
	public List<AttachFile> findFilesByHql(String hql,String orderByHql);
	
	@SuppressWarnings("unchecked")
	List findByHQL(String hql,Object... params);
	
	HibernateTemplate getHibernateTemplate();

}
