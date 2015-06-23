package com.wonders.stpt.standardWork.service;

import java.util.List;

public interface StandardWorkService {
	public List<Object[]> findEventsTypeList();
	
	public String findEventsTypeByTree(String openId);
	
	public List<Object[]> getFileListByPage(int startRow, int pageSize,String typeId, String searchParam);
	
	public int getFileListCount(String typeId, String searchParam);
	
	public String getFilePathById(String id);
	
	public Object load(Class entityClass,String id);
	
	public String getParentNodes(String id);
	
	public List<Object[]> getContentListByPage(int startRow, int pageSize);
	
	public int getContentListCount();
}
