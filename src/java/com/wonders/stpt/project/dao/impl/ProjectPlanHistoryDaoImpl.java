package com.wonders.stpt.project.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.project.dao.ProjectPlanHistoryDao;
import com.wonders.stpt.project.model.ProjectPlanHistory;
/**
 * 历史计划信息Dao实现
 * @author mengjie
 *
 */
@Repository("projectPlanHistoryDao")
public class ProjectPlanHistoryDaoImpl implements ProjectPlanHistoryDao {

	private HibernateTemplate hibernateTemplate;
	private Logger logger=Logger.getLogger(ProjectPlanHistoryDaoImpl.class);
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
	public List<ProjectPlanHistory> find(String projectPlanId) {
		// TODO Auto-generated method stub
		HashMap parameter=new HashMap();
		StringBuffer sb=new StringBuffer();
		String sql="from ProjectPlanHistory p where 1=1 and p.projectPlanId=:projectPlanId ";
		parameter.put("projectPlanId", projectPlanId);
		sb.append(sql);
		
		return getSession().createQuery(sb.toString()).setProperties(parameter).list();
	}

	@Override
	public void saves(List<ProjectPlanHistory> historyList) {
		// TODO Auto-generated method stub
		logger.error("saveOrUpdate");
		hibernateTemplate.saveOrUpdateAll(historyList);
	}

}
