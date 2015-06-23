package com.wonders.stpt.ThemeDatabase.service;

import java.util.List;

public interface ThemeDatabaseService {
public List<Object[]> findBySql(String sql,List<Object> src);
	
	public Object load(String id,Class entityClass);
}
