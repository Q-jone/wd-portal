package com.wonders.stpt.deptDoc.service;

import java.util.List;

import com.wonders.stpt.deptDoc.model.bo.ZDocsRight;
import com.wonders.stpt.deptDoc.model.vo.ZDocsFileListVo;

public interface ZDocsRightService {
	
	public void initRights(ZDocsFileListVo vo, String loginName, String deptId);
	
	public ZDocsRight findById(String catalogId);
	
	public int grantToEmps(String rightId, String empIds, String empNames, String rightType, String uUserId, boolean override);
	
	public List<ZDocsRight> findByRightId(String rightId);
	
	public int authoity(String fileIds,String empIds,String empNames,String loginName,String userName);
	
	public int cancel(String[] fileIds,String empIds[]);
}
