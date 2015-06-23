package com.wonders.stpt.standardWork.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.standardWork.dao.StandardWorkDao;

@Repository("standardWorkDao")
public class StandardWorkDaoImpl implements StandardWorkDao{
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object load(Class entityClass,String id){
		return this.getHibernateTemplate().load(entityClass, id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> findEventsTypeList(){
		String sql = "select t.id,t.type_name,t.parent_id,t.node_order,t.node_level,t.parent_type_name " +
				" from standard_work_type t where t.removed = '0' " +
				" connect by prior t.id = t.parent_id start with t.parent_id = '0' " +
				" order SIBLINGS by t.node_order";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("id", Hibernate.STRING).addScalar("type_name", Hibernate.STRING)
				.addScalar("parent_id", Hibernate.STRING).addScalar("node_order", Hibernate.INTEGER)
				.addScalar("node_level", Hibernate.INTEGER).addScalar("parent_type_name", Hibernate.STRING);
		List<Object[]> list = query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFileListByPage(int startRow, int pageSize,String typeId, String searchParam){
		String sql = "select * from standard_work_file t where removed = '0' and t.type_id = ? ";
		if(searchParam!=null&&searchParam.length()>0){
			sql += " and (t.file_name like ? or t.file_code like ?) ";
		}
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, typeId);
		if(searchParam!=null&&searchParam.length()>0){
			query.setString(1, "%"+searchParam+"%").setString(2, "%"+searchParam+"%");
		}
		return query.setFirstResult(startRow).setMaxResults(pageSize).list();
	}
	
	public int getFileListCount(String typeId, String searchParam){
		String sql = "select count(*) count_num from standard_work_file t where removed = '0' and t.type_id = ? ";
		if(searchParam!=null&&searchParam.length()>0){
			sql += " and (t.file_name like ? or t.file_code like ?) ";
		}
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, typeId);
		if(searchParam!=null&&searchParam.length()>0){
			query.setString(1, "%"+searchParam+"%").setString(2, "%"+searchParam+"%");
		}
		return (Integer)query.addScalar("count_num", Hibernate.INTEGER).uniqueResult();
	}
	
	public String getFilePathById(String id){
		String sql = "select file_path from standard_work_file where id = ?";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return (String)query.addScalar("file_path", Hibernate.STRING).setString(0, id).uniqueResult();
	}
	
	public String getTypeNameById(String id){
		String sql = "select type_name from standard_work_type where id = ?";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return (String)query.addScalar("type_name", Hibernate.STRING).setString(0, id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getContentListByPage(int startRow, int pageSize){
		String sql = "select * from standard_content t ";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.setFirstResult(startRow).setMaxResults(pageSize).list();
	}
	
	@SuppressWarnings("unchecked")
	public int getContentListCount(){
		String sql = "select count(*) count_num from standard_content t ";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return (Integer)query.addScalar("count_num", Hibernate.INTEGER).uniqueResult();
	}
}
