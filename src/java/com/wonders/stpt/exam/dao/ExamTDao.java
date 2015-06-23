package com.wonders.stpt.exam.dao;

import java.util.Collection;
import java.util.List;

import com.wonders.stpt.page.model.PageResultSet;

public interface ExamTDao<T> {
	
	public T find(String id,Class<T> c);
	
	public List<T> findByHql(String hql);
	
	public T save(T t);
	public T update(T t);
	public void saveOrUpdateAll(Collection cols);
	
	public int updateBySql(String sql);
	
	public PageResultSet findPageResult(String queryHql, String countHql, int pageNo, int pageSize);
	
	public List<T> findByPage(String hql,int startRow, int pageSize);
	
	public int excuteHQLUpdate(final String hql, final Object[] obj);
	
	public List excuteSQLQuery(final String sql, final Object[] obj);

}
