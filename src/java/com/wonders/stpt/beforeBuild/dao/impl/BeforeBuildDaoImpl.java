package com.wonders.stpt.beforeBuild.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.beforeBuild.dao.BeforeBuildDao;

@Repository("beforeBuildDao")
public class BeforeBuildDaoImpl implements BeforeBuildDao{
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void save(Object obj){
		hibernateTemplate.saveOrUpdate(obj);
	}
	
	public void saveOrUpdateAll(Collection cols){
		hibernateTemplate.saveOrUpdateAll(cols);
	}
	
	public Object load(String id,Class entityClass){
		return hibernateTemplate.get(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findByPage(int first,int size,String sql,List<Object> src){
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if(src!=null&&src.size()>0){
			for(int i=0;i<src.size();i++){
				query.setParameter(i, src.get(i));
			}
		}
		query.setFirstResult(first);
		query.setMaxResults(size);
		List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		return list;
	}
	
	public int findPageSize(String sql,List<Object> src){
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		if(src!=null&&src.size()>0){
			for(int i=0;i<src.size();i++){
				query.setParameter(i, src.get(i));
			}
		}
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer)query.uniqueResult();
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
	
	public void updateBySql(String sql,List<Object> src){
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		if(src!=null&&src.size()>0){
			for(int i=0;i<src.size();i++){
				query.setParameter(i, src.get(i));
			}
		}
		query.executeUpdate();
	}
}
