package com.wonders.stpt.section;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;


public class AuditDaoImpl  extends AbstractHibernateDaoImpl<Object> {
	
	public int findIfActiveUser(String login_name){
		String sql = "select count(distinct u.login_name) as count_num from ca_visit_log c,t_user_relation t,t_user u where c.appname='AUTO_LOGIN' and c.visit_time>sysdate-14 and t.c_id = c.userid and u.id = t.t_id and u.login_name= '"+login_name+"'";
		Session session = this.getSession();
		Transaction tran = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		int result = (Integer)query.uniqueResult();
		tran.commit();
		session.flush();
		session.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findDataFromCaAuditInfo(String login_name,String dept_id){
		String sql = "select t.ext1,t.ext2,t.ext3,t.ext4,t.ext5,t.ext6 from ca_audit_info t where t.login_name = '"+login_name+"' and t.dept_id = '"+dept_id+"' and t.removed = '0'";
		Session session = this.getSession();
		Transaction tran = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("ext1", Hibernate.STRING).addScalar("ext2", Hibernate.STRING).addScalar("ext3", Hibernate.STRING)
			.addScalar("ext4", Hibernate.STRING).addScalar("ext5", Hibernate.STRING).addScalar("ext6", Hibernate.STRING);
		List list = query.list();
		tran.commit();
		session.flush();
		session.close();
		return list;
	}
}
