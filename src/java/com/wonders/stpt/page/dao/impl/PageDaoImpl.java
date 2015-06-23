/**
 * 
 */
package com.wonders.stpt.page.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.page.dao.PageDao;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;

/** 
 * @ClassName: PageDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午07:19:44 
 *  
 */
@Repository("pageDao")
public class PageDaoImpl implements PageDao{

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int countBySql(String sql) {
		List list =this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
		return list.size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int countByHql(String hql) {
		List list = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql).list();
		//System.out.println(hql);
		return list.size();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> findPaginationInfo(String sql, int startRow, int pageSize) {
		List list = new ArrayList();
		List<String[]> finlist = new ArrayList();
		SQLQuery query  = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		Iterator<String[]> it = list.iterator();
		while(it.hasNext()){
			Object[] objArr = (Object[])it.next();
			String[] ret = new String[objArr.length];
			for(int i=0; i<objArr.length; i++){
				ret[i] = StringUtil.getNotNullValueString(objArr[i]);
				//System.out.println(ret[i]);
			}
			finlist.add(ret);
		}
		return finlist;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List findPaginationInfoByHql(String hql, int startRow, int pageSize,Map<String, Object> filter,String orderSql) {
		
		List list = new ArrayList();
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		
		String filterPart = "";
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				filterPart += "t." + paramName + " like :" + paramName;
				args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
			}
		}
		hql = hql + filterPart + orderSql;
		//System.out.println(hql);
		Query query  = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		com.wonders.stpt.page.util.HqlUtils.setQueryParameters(query, args);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}
	
	
	public List findPageInfo(String sql , int startRow, int pageSize,Map<String,Object> filter){
		List list = new ArrayList();
		if (filter == null){
			filter = new HashMap<String, Object>();
		}
		Query query  = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if(!filter.isEmpty()){
			int i = 0;
			for(Map.Entry<String, Object> entry : filter.entrySet()){
				Object argValue = entry.getValue();
				query.setParameter(i, argValue);
			}
		}
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}
	
	public int countBySql(String sql,List filter) {
		List list = new ArrayList();
		Query query  = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (filter == null){
			filter = new ArrayList();
		}
		if(!filter.isEmpty()){
			for(int i =0;i<filter.size();i++){
				query.setParameter(i, filter.get(i));
			}
		}
		return query.list().size();
	}
	
	public List findPageInfo(String sql , int startRow, int pageSize,List filter){
		List list = new ArrayList();
		if (filter == null){
			filter = new ArrayList();
		}
		Query query  = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if(!filter.isEmpty()){
			for(int i =0;i<filter.size();i++){
				query.setParameter(i, filter.get(i));
			}
		}
		
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		List<String[]> finlist = new ArrayList();
		Iterator<String[]> it = list.iterator();
		while(it.hasNext()){
			Object[] objArr = (Object[])it.next();
			String[] ret = new String[objArr.length];
			for(int i=0; i<objArr.length; i++){
				ret[i] = StringUtil.getNotNullValueString(objArr[i]);
				//System.out.println(ret[i]);
			}
			finlist.add(ret);
		}
	
		return finlist;
	}
}
