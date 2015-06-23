package com.wonders.stpt.operation.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wonders.stpt.operation.entity.bo.OpDocSend;
import com.wonders.stpt.page.model.PageResultSet;

public interface OpDocSendService {
	
	public OpDocSend find(String id);

	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public OpDocSend save(OpDocSend bo,HttpServletRequest request);
	
	public int deleteById(String id);
	
	public int updateAttMain(String id,String contentAttMain);

    public int updateIsLeader(String id,String isLeader);
	
	public OpDocSend findByFlowUid(String flowUid);
	
	public List<Object[]> getDeptLeaders(String deptId);
	
	public List<Object[]> getUrgentApprovers();
	
	public String genFileCode(String deptId);
	
	public Object[] getReadInfo(String mainId,String deptId);
}
