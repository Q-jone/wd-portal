package com.wonders.stpt.build.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.build.dao.BuildDao;
import com.wonders.stpt.build.service.BuildService;

@Repository("buildService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class BuildServiceImpl implements BuildService{
	@Autowired
	private BuildDao buildDao;
	
	public List<Object[]> getProcessData(){
		return buildDao.getProcessData();
	}
	
	public List<Object[]> getProcessList(String processType){
		return buildDao.getProcessList(processType);
	}
	
	public List<Object[]> getProcessDetailList(String processType,String project_id,String detailType){
		return buildDao.getProcessDetailList(processType,project_id,detailType);
	}
	
	public List<Object[]> getTasksList(String project_id,String plan_id,String processType,String detailType,String type){
		return buildDao.getTasksList(project_id, plan_id, processType, detailType, type);
	}

	public List<Object[]> getProcessYearCompareList(String year,String project_name,String type_name){
		return buildDao.getProcessYearCompareList(year,project_name,type_name);
	}
	
	public List<Object[]> getProcessMonthCompareList(String year,String stat_month,String type_name,String project_name){
		return buildDao.getProcessMonthCompareList(year,stat_month,type_name,project_name);
	}
	
	public List getListBySql(String sql){
		return buildDao.getListBySql(sql);
	}
	
	public List<Object[]> findProcessPlanComparision(String projectId,String dateType){
		return buildDao.findProcessPlanComparision(projectId,dateType);
	}
}
