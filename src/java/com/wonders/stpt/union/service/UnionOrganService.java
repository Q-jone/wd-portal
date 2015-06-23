package com.wonders.stpt.union.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.union.entity.bo.UnionDealUser;

public interface UnionOrganService {
	
	public List<Map> getDepts();
	
	public List<Map> getDirectDepts();
	
	public List<Map> getEmployees(String deptId);
	
	public Map getSingleLeader(String deptId);
	
	public List<Map> getDeptLeaders(String deptId);
	
	public List<Map> fuzzySearchEmployees(String deptId, String key);
}
