package com.wonders.stpt.contractPortal.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.contractPortal.dao.ContractPortalDao;

@Repository("contractPortalDao")
public class ContractPortalDaoImpl implements ContractPortalDao{
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatasBySql(String sql){
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		return query.list();
	}
	
	public void saveOrUpdateAll(Collection cols){
		hibernateTemplate.saveOrUpdateAll(cols);
	}
	
	public void updateDatasBySql(String sql){
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
	}
	
}
