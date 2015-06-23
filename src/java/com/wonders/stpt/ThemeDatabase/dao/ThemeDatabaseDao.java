package com.wonders.stpt.ThemeDatabase.dao;

import java.util.List;

public interface ThemeDatabaseDao {
	public List<Object[]> findBySql(String sql,List<Object> src);
	
	public Object load(String id,Class entityClass);
}
