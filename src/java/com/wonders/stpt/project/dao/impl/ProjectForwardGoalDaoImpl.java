package com.wonders.stpt.project.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.ProjectForwardGoalDao;
import com.wonders.stpt.project.model.ProjectForwardGoal;
@Repository("projectForwardGoalDao")
public class ProjectForwardGoalDaoImpl implements ProjectForwardGoalDao {

	private HibernateTemplate hibernateTemplate;
    private final Logger logger=Logger.getLogger(ProjectForwardGoalDaoImpl.class);
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Resource(name = "hibernateTemplate")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }
	
	
	@Override
	public ProjectForwardGoal save(ProjectForwardGoal obj) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(obj);
		return obj;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return getSession().createQuery("update ProjectForwardGoal p set p.removed=:removed where p.forwardGoalId=:forwardGoalId").setString("removed", "1").setString("forwardGoalId", id).executeUpdate();
	}

	@Override
	public ProjectForwardGoal load(String id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.load(ProjectForwardGoal.class,new String(id));
	}

	@Override
	public List<ProjectForwardGoal> find(String projectId, Date begin, Date end) {
		// TODO Auto-generated method stub
		HashMap parameter = new HashMap();
		parameter.put("projectId", projectId);
		StringBuffer sb=new StringBuffer();
		sb.append("from ProjectForwardGoal p where p.project.projectId = :projectId and p.removed='0' ");
		return getSession().createQuery(sb.toString()).setProperties(parameter).list();
	}

	@Override
	public PageResultSet<ProjectForwardGoal> projectForwardGoals(
			String projectId,int page,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public void saves(List<ProjectForwardGoal> projectFrowardGoals)
			throws Exception {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdateAll(projectFrowardGoals);
	}

}
