package com.wonders.stpt.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.project.dao.ProjectPlanHistoryDao;
import com.wonders.stpt.project.model.ProjectPlanHistory;
import com.wonders.stpt.project.service.ProjectPlanHistoryService;

@Repository("projectPlanHistoryService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ProjectPlanHistoryServiceImpl implements ProjectPlanHistoryService {

	@Autowired
	private ProjectPlanHistoryDao projectPlanHistoryDao;
	@Override
	public List<ProjectPlanHistory> getProjectPlanHistorys(String projectPlanId)
			throws Exception {
		// TODO Auto-generated method stub
		return projectPlanHistoryDao.find(projectPlanId);
	}

	@Override
	public void save(List<ProjectPlanHistory> projectPlanHistory)throws Exception {
		// TODO Auto-generated method stub
		projectPlanHistoryDao.saves(projectPlanHistory);
	}

}
