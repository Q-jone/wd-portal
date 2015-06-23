package com.wonders.stpt.train.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.train.dao.TrainTDao;
import com.wonders.stpt.train.entity.TrainMain;
import com.wonders.stpt.util.DbUtil;
@SuppressWarnings("unchecked")
@Repository("trainTDao")
public class TrainTDaoImpl<T>  implements TrainTDao<T>{
	
private HibernateTemplate hibernateTemplate;
private DbUtil dbUtil;
public  DbUtil getDbUtil() {
	return dbUtil;
}
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	public int updateBySql(String sql){
		return dbUtil.getJdbcTemplate().update(sql);
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@Override
	public T find(String id,Class<T> c) {
		
		String hql = "from "+c.getName()+" t where t.id = ? ";
		List<T> list = this.hibernateTemplate.find(hql, new Object[]{id});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<T> findByHql(String hql) {
		
		return this.hibernateTemplate.find(hql);
	}
	@Override
	public T save(T t) {
		this.hibernateTemplate.save(t);
		return t;
	}
	@Override
	public T update(T t) {
		this.hibernateTemplate.update(t);
		return t;
	}
	@Override
	public void deleteById(String id,Class<T> c) {
		this.hibernateTemplate.delete(hibernateTemplate.get(c,id));
	}
	@Override
	public void saveOrUpdateAll(Collection cols){
		this.hibernateTemplate.saveOrUpdateAll(cols);
	}
	
}
