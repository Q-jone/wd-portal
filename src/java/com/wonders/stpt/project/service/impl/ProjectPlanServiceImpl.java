package com.wonders.stpt.project.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.ProjectPlanDao;
import com.wonders.stpt.project.model.ProjectPlan;
import com.wonders.stpt.project.model.ProjectPlanHistory;
import com.wonders.stpt.project.service.ProjectPlanService;
/**
 * 项目计划信息实现Service
 * @author mengjie
 *
 */
@Repository("projectPlanService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ProjectPlanServiceImpl implements ProjectPlanService {

	private final Logger logger=Logger.getLogger(ProjectPlanServiceImpl.class);
	@Autowired
	private ProjectPlanDao projectPlanDao;
	 
	@Override
	public ProjectPlan getProjectPlan(String projectPlanId) throws Exception {
		// TODO Auto-generated method stub
		ProjectPlan projectPlan=null;
		if(StringUtils.isBlank(projectPlanId)){
			Exception ex=new Exception("根据项目计划projectPlanId查询时出错：参数格式不正确,"+projectPlanId);
			logger.error(ex.getMessage());
			throw new Exception(ex);
		}else{
			try {
				projectPlan = projectPlanDao.load(projectPlanId);
			} catch (Exception ex) {
				// TODO: handle exception
				logger.error("查找项目计划出错：projectPlanId="+projectPlanId);
                throw ex;
			}
		}
		return projectPlan;
	}

	@Override
	public PageResultSet<ProjectPlan> getProjectPlans(ProjectPlan projectPlan, int page, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return projectPlanDao.find(projectPlan, page, pageSize);
	}

	@Override
	public int deleteProjectPlan(String projectPlanId) throws Exception {
		// TODO Auto-generated method stub
		return deleteProjectPlans(new String[]{projectPlanId});
	}

	@Override
	public List<ProjectPlanHistory> getProjectPlanHistorys(String projectPlanId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectPlan save(ProjectPlan projectPlan) throws Exception {
		// TODO Auto-generated method stub
		return projectPlanDao.save(projectPlan);
	}

	@Override
	public void save(List<ProjectPlan> projectPlans) throws Exception {
		// TODO Auto-generated method stub
		logger.error("service");
		projectPlanDao.saveOrUpdateAll(projectPlans);
	}

	@Override
	public int deleteProjectPlans(String[] projectPlanIds) throws Exception {
		// TODO Auto-generated method stub
		return projectPlanDao.delete(projectPlanIds);
	}

}
