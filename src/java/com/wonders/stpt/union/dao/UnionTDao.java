package com.wonders.stpt.union.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.wonders.stpt.page.model.PageResultSet;

public interface UnionTDao<T> {
	
	public HibernateTemplate getHibernateTemplate();
	
	public T find(String id,Class<T> c);
	
	public T find(Long id,Class<T> c);
	
	public List<T> findByHql(String hql);
	
	public List<T> findByHql(String hql,Object[] obj);
	
	public int countByHql(String queryHql);
	
	public T save(T t);
	public T update(T t);
	public void saveOrUpdateAll(Collection cols);
	
	public int updateBySql(String sql);
	
	public PageResultSet findPageResult(String queryHql, String countHql, int pageNo, int pageSize);
	
	public List<T> findByPage(String hql,int startRow, int pageSize);
	
	public int excuteHQLUpdate(final String hql, final Object[] obj);
	
	public List excuteSQLQuery(final String sql, final Object[] obj);
	
	public List<Map> findBySql(String sql);

	public int updateSQLQuery(final String sql,final Map<String,Object[]> param);
}
