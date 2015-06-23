package com.wonders.stpt.project.service;

import java.util.List;

import com.wonders.stpt.project.model.ProjectPlanHistory;

/**
 * 历史计划信息服务
 * @author mengjie
 *
 */
public interface ProjectPlanHistoryService {
	/**
	 * 根据项目计划主键Id查询历史计划信息
	 * @param projectPlanId
	 * @return
	 */
	List<ProjectPlanHistory> getProjectPlanHistorys(String projectPlanId)throws Exception;
	
	/**
	 * 批量添加或修改历史计划信息
	 * @param projectPlanHistory
	 */
	void save(List<ProjectPlanHistory> projectPlanHistory)throws Exception;

}
