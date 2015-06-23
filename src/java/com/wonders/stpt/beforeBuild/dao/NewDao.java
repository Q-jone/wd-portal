package com.wonders.stpt.beforeBuild.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface NewDao {
	public List<Object[]> findBySql(String sql,List<Object> src);
}
