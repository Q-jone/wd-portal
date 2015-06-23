package com.wonders.stpt.ThemeDatabase.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.ThemeDatabase.dao.ThemeDatabaseDao;

@Repository("themeDatabaseDao")
public class ThemeDatabaseDaoImpl implements ThemeDatabaseDao{
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public Object load(String id,Class entityClass){
		return hibernateTemplate.get(entityClass, id);
	}
	
	public List<Object[]> findBySql(String sql,List<Object> src){
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		if(src!=null&&src.size()>0){
			for(int i=0;i<src.size();i++){
				query.setParameter(i, src.get(i));
			}
		}
		return query.list();
	}
}
