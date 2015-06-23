package com.wonders.stpt.beforeBuild.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BeforeBuildService {
	public void save(Object obj);
	
	public void saveOrUpdateAll(Collection cols);
	
	public Object load(String id,Class entityClass);
	
	public List<Map<String,Object>> findByPage(int first,int size,String sql,List<Object> src);
	
	public int findPageSize(String sql,List<Object> src);
	
	public List<Object[]> findBySql(String sql,List<Object> src);
	
	public void updateBySql(String sql,List<Object> src);
}
