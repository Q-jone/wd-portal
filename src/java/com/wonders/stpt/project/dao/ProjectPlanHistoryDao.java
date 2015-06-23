package com.wonders.stpt.project.dao;

import java.util.List;

import com.wonders.stpt.project.model.ProjectPlanHistory;

/**
 * 历史计划DAO
 * @author mengjie
 *
 */
public interface ProjectPlanHistoryDao {

	/**
	 * 根据项目计划主键ID查找历史计划信息
	 * @param projectPlanId
	 * @return
	 */
	List<ProjectPlanHistory> find(String projectPlanId)throws Exception;
	
	/**
	 * 批量添加项目计划历史信息
	 * @param historyList
	 */
	void saves(List<ProjectPlanHistory> historyList)throws Exception;
}
