/**
 * 
 */
package com.wonders.stpt.processStop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.wonders.stpt.processStop.model.bo.ProcessStop;
import com.wonders.stpt.processStop.dao.ProcessStopDao;

/** 
 * @ClassName: ProcessStopDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午10:04:48 
 *  
 */
@Repository("processStopDao")
public class ProcessStopDaoImpl implements ProcessStopDao{
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public ProcessStop save(ProcessStop bo){
		this.hibernateTemplate.save(bo);
		return bo;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcessStop> find(String id){
		String hql = "from ProcessStop t where t.removed='0' and mainId = ? ";
		List<ProcessStop> list = this.hibernateTemplate.find(hql, new Object[]{id});
		return list;
	}
	
	
	private String getSql(String table){
		String sql  =  "";
		if("t_doc_receive".equals(table)){
			sql = "select t.modelid as pname,t.instanceid as incident,t.title,t.flag as status,t.operate_time as time,t.id " +
					" from t_doc_receive t where  t.modelid in ('收文流程','新收文流程')" +
					" and t.instanceid is not null and " +
					" ((t.removed = 0 and t.flag in ('0','1','2')) or (t.removed=1 and t.flag ='99'))";

			sql = "select * from (" + sql + ") where 1=1";
		}else if("t_doc_send".equals(table)){
			sql = "select t.modelid as pname,t.pinstanceid as incident,t.doc_title as title,t.flag as status,t.operate_time as time,t.id  " +
					" from t_doc_send t where  t.modelid in ('发文流程','新发文流程')" +
					" and t.pinstanceid is not null and " +
					" ((t.removed = 0 and t.flag in ('0','1','2')) or (t.removed=1 and t.flag ='99'))";
			
			sql = "select * from (" + sql + ") where 1=1";
		}else if("t_receive_directive".equals(table)){
			sql = "select t.activeid as pname,t.processinstanceid as incident,t.title,t.flag as status,t.operate_time as time,t.id  " +
					" from t_receive_directive t where  t.activeid in ('收呈批件','新收呈批件')" +
					" and t.processinstanceid is not null and " +
					" ((t.removed = 0 and t.flag in ('0','1','2')) or (t.removed=1 and t.flag ='99'))";
					
			sql = "select * from (" + sql + ") where 1=1";
		}
		return sql;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByPage(int startRow,int pageSize,String table,
			String title,String status,String starttime,String endtime){
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = getFullSql(getSql(table), table,
				title,status,starttime,endtime);
		sql += " order by time desc ";
		//System.out.println("sql=="+sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
		
	}
	
	public int count(String table,
			String title,String status,String starttime,String endtime){
		String sql = getFullSql(getSql(table), table,
				title,status,starttime,endtime);
		System.out.println("sql=="+sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num from ("+sql+") ");
		query.addScalar("count_num", Hibernate.INTEGER);
		
		return (Integer)query.uniqueResult();
		
	}
	
	
	
	private String getFullSql(String sql,String table,String title,String status,
			String starttime,String endtime){
		
		if(title!=null&&!"".equals(title)){
			sql += " and title like '%"+title+"%' ";
		}
		if(status!=null&&!"".equals(status)){
			sql += " and status = '"+status+"' ";
		}
		if(starttime!=null&&!"".equals(starttime)){
			sql += " and time >= '"+starttime+"' ";
		}
		if(endtime!=null&&!"".equals(endtime)){
			sql += " and time <= '"+endtime+"' ";
		}
		
		
		return sql;
	}
}
