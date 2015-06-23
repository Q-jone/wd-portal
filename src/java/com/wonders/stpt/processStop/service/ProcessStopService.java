/**
 * 
 */
package com.wonders.stpt.processStop.service;

import java.util.List;

import com.wonders.stpt.processStop.model.bo.ProcessStop;


/** 
 * @ClassName: ContractManageService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午9:56:34 
 *  
 */
public interface ProcessStopService {
	public List<Object[]> findByPage(int startRow,int pageSize,String table,
			String title,String status,String starttime,String endtime);
	
	public int count(String table,
			String title,String status,String starttime,String endtime);
	
	
	public boolean save(String reason,String attach,String operator,String mainId,String process,String incident);
	
	public List<ProcessStop> find(String id);
}
