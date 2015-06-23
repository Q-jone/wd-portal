package com.wonders.stpt.train.dao;

import java.util.Collection;
import java.util.List;

public interface TrainTDao<T> {
	
	public T find(String id,Class<T> c);
	
	public List<T> findByHql(String hql);
	
	public T save(T t);
	public T update(T t);
	public void saveOrUpdateAll(Collection cols);
	
	public int updateBySql(String sql);
	
	public void deleteById(String id,Class<T> c);

}
