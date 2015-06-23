/**
 * 
 */
package com.wonders.stpt.processStop.dao;

import java.util.List;

import com.wonders.stpt.processStop.model.bo.ProcessStop;

/** 
 * @ClassName: ProcessStopDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午10:04:54 
 *  
 */
public interface ProcessStopDao {
	public List<Object[]> findByPage(int startRow,int pageSize,String table,
			String title,String status,String starttime,String endtime);
	public int count(String table,
			String title,String status,String starttime,String endtime);
	
	public ProcessStop save(ProcessStop bo);
	
	public List<ProcessStop> find(String id);
}
