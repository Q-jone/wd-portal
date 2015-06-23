package com.wonders.stpt.pxzx.service;

import java.util.List;

public interface PxzxService {
	public List<Object[]> findBySql(String sql,List<Object> src);
}
