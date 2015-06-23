package com.wonders.stpt.build.service;

import java.util.List;

public interface BuildService {
	public List<Object[]> getProcessData();
	
	public List<Object[]> getProcessList(String processType);
	
	public List<Object[]> getProcessDetailList(String processType,String project_id,String detailType);
	
	public List<Object[]> getTasksList(String project_id,String plan_id,String processType,String detailType,String type);
	
	public List<Object[]> getProcessYearCompareList(String year,String project_name,String type_name);
	
	public List<Object[]> getProcessMonthCompareList(String year,String stat_month,String type_name,String project_name);
	
	public List getListBySql(String sql);
	
	public List<Object[]> findProcessPlanComparision(String projectId,String dateType);
}
