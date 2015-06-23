/**
 * 
 */
package com.wonders.stpt.pageNew.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.pageNew.dao.PageNewDao;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;

/** 
 * @ClassName: PageDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午07:19:44 
 *  
 */
@Repository("pageNewDao")
public class PageNewDaoImpl implements PageNewDao{

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
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
			try {
				Object[] objArr = (Object[])it.next();
				String[] ret = new String[objArr.length];
				for(int i=0; i<objArr.length; i++){
					ret[i] = StringUtil.getNotNullValueString(objArr[i]);
				}
				finlist.add(ret);
			} catch (Exception e) {
				String[] arr = new String[1];
				arr[0] = list.get(0).toString();
				finlist.add(arr);
				return finlist;
			}
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
	
}
