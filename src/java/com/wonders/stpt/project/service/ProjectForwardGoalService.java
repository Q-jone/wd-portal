package com.wonders.stpt.project.service;

import java.util.List;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.ProjectForwardGoal;

public interface ProjectForwardGoalService {

	/**
	 * 分页查询显示
	 * @param projectId
	 * @param page
	 * @param ageSize
	 * @return
	 * @throws Exception
	 */
	PageResultSet<ProjectForwardGoal> projectForwardGoals(String projectId,int page,int pageSize)throws Exception;
	/**
	 * 批量保存记录
	 * @param ProjectForwardGoals
	 * @throws Exception
	 */
	void saves(List<ProjectForwardGoal> ProjectForwardGoals)throws Exception;
	/**
	 * 根据projectId查找
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	List<ProjectForwardGoal> projectForwardGoal(String projectId)throws Exception;
	/**
	 * 单条记录保存
	 * @param p
	 * @throws Exception
	 */
	void saves(ProjectForwardGoal p)throws Exception;
}
