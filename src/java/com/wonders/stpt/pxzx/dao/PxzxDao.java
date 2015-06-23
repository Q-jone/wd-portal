package com.wonders.stpt.pxzx.dao;

import java.util.List;

public interface PxzxDao {
	public List<Object[]> findBySql(String sql,List<Object> src);
}
