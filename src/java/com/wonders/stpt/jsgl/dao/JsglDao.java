package com.wonders.stpt.jsgl.dao;

import java.util.List;

public interface JsglDao {
	public List<Object[]> findBySql(String sql,List<Object> src);
}
