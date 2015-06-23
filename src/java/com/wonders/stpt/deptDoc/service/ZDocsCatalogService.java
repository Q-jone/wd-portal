package com.wonders.stpt.deptDoc.service;

import java.util.List;

import com.wonders.stpt.deptDoc.model.bo.ZDocsCatalog;

public interface ZDocsCatalogService {
	public ZDocsCatalog findById(String catalogId);
	
	public int removeById(String catalogId);
	
	public ZDocsCatalog saveDir(String id, String pid, String catalogName, String empIds, String empNames, String cUserId,String cUserName);

	public List getFolders(String flag, String loginName, String deptId);
	
	public List getSelectableFolders(String flag, String loginName, String deptId,String catalogId);
}
