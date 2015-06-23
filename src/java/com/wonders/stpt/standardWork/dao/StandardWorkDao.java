package com.wonders.stpt.standardWork.dao;

import java.util.List;

public interface StandardWorkDao {
	public Object load(Class entityClass,String id);
	
	public List<Object[]> findEventsTypeList();
	
	public List<Object[]> getFileListByPage(int startRow, int pageSize,String typeId, String searchParam);
	
	public int getFileListCount(String typeId, String searchParam);
	
	public String getFilePathById(String id);
	
	public String getTypeNameById(String id);
	
	public List<Object[]> getContentListByPage(int startRow, int pageSize);
	
	public int getContentListCount();
}
