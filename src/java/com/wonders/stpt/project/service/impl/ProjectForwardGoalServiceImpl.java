package com.wonders.stpt.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.ProjectForwardGoalDao;
import com.wonders.stpt.project.model.ProjectForwardGoal;
import com.wonders.stpt.project.service.ProjectForwardGoalService;
@Repository("projectForwardGoalService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectForwardGoalServiceImpl implements ProjectForwardGoalService {

	@Autowired
	private ProjectForwardGoalDao projectForwardGoalDao;
	@Override
	public PageResultSet<ProjectForwardGoal> projectForwardGoals(
			String projectId, int page, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return projectForwardGoalDao.projectForwardGoals(projectId, page, pageSize);
	}

	@Override
	public void saves(List<ProjectForwardGoal> ProjectForwardGoals)
			throws Exception {
		// TODO Auto-generated method stub
		projectForwardGoalDao.saves(ProjectForwardGoals);
	}

	@Override
	public List<ProjectForwardGoal> projectForwardGoal(String projectId)
			throws Exception {
		// TODO Auto-generated method stub
		return projectForwardGoalDao.find(projectId, null, null);
	}

	@Override
	public void saves(ProjectForwardGoal p) throws Exception {
		// TODO Auto-generated method stub
		projectForwardGoalDao.save(p);
	}

}
