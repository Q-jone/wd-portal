package com.wonders.stpt.deptDoc.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.deptDoc.model.bo.ZDocsRight;
import com.wonders.stpt.deptDoc.model.vo.PageQueryVo;
import com.wonders.stpt.page.model.PageResultSet;

public interface DeptDocProcessDao {
	//list
	public PageResultSet<Map<String,Object>> list(String sourceSql,PageQueryVo vo);
	
	//设置权限
	public boolean authority(List<ZDocsRight> rights);
	
	//取消权限
	public boolean cancel(final String[] fileId,final String[] empId);
	
}
