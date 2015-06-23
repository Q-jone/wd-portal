package com.wonders.stpt.deptDoc.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.deptDoc.model.bo.ZDocsRight;
import com.wonders.stpt.deptDoc.model.vo.PageQueryVo;
import com.wonders.stpt.page.model.PageResultSet;

public interface DeptDocProcessService {
	public PageResultSet<Map<String,Object>> list(String sourceSql,PageQueryVo vo);
	public boolean authority(List<ZDocsRight> rights);
	public boolean cancel(String[] fileId,String[] empId);
}
