package com.wonders.stpt.project.service;

import java.util.List;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.ProjectPlan;
import com.wonders.stpt.project.model.ProjectPlanHistory;

/**
 * 项目计划接口
 * @author mengjie
 *
 */
public interface ProjectPlanService {

	/**
	 * 根据项目计划主键查询项目计划信息
	 * @param projectPlanId
	 * @return
	 * @throws Exception
	 */
	ProjectPlan getProjectPlan(String projectPlanId)throws Exception;
	
	/**
	 * 根据项目计划查询条件查询项目计划
	 * @param projectId
	 * @param projectPlan
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageResultSet<ProjectPlan> getProjectPlans(ProjectPlan projectPlan,int page,int pageSize)throws Exception;
	
	/**根据项目计划主键删除记录
	 * 
	 * @param projectPlanId
	 * @return
	 * @throws Exception
	 */
	int deleteProjectPlan(String projectPlanId)throws Exception;
	
	int deleteProjectPlans(String[] projectPlanIds)throws Exception;
	
	/**
	 * 根据项目计划主键查询历史信息记录
	 * @param projectPlanId
	 * @return
	 * @throws Exception
	 */
	List<ProjectPlanHistory> getProjectPlanHistorys(String projectPlanId)throws Exception;
	
	/**
	 * 根据参数对像新增项目计划信息记录
	 * @param projectPlan
	 * @return
	 * @throws Exception
	 */
	ProjectPlan save(ProjectPlan projectPlan)throws Exception;
	
	/**
	 * 根据项目计划信息参数集合进行批量新增
	 * @param projectPlans
	 * @throws Exception
	 */
	void save(List<ProjectPlan> projectPlans)throws Exception;
}
