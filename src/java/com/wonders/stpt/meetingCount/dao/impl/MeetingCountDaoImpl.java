package com.wonders.stpt.meetingCount.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.meetingCount.dao.MeetingCountDao;

@Repository("meetingCountDao")
public class MeetingCountDaoImpl implements MeetingCountDao{
private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
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
