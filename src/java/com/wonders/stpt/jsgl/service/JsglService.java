package com.wonders.stpt.jsgl.service;

import java.util.List;

public interface JsglService {
	public List<Object[]> findBySql(String sql,List<Object> src);
}
