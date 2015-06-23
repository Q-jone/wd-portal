package com.wonders.stpt.beforeBuild.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface NewService {
	public List<Object[]> findBySql(String sql,List<Object> src);
}
