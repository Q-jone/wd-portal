/**
 * 
 */
package com.wonders.stpt.contractManage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.contractManage.dao.ContractManageDao;
import com.wonders.stpt.contractManage.model.bo.HtxxStop;

/** 
 * @ClassName: ContractManageDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午10:04:48 
 *  
 */
@Repository("contractManageDao")
public class ContractManageDaoImpl implements ContractManageDao{
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public HtxxStop save(HtxxStop bo){
		this.hibernateTemplate.save(bo);
		return bo;
	}
	
	@SuppressWarnings("unchecked")
	public List<HtxxStop> find(String id){
		String hql = "from HtxxStop t where t.removed='0' and mainId = ? ";
		List<HtxxStop> list = this.hibernateTemplate.find(hql, new Object[]{id});
		return list;
	}
	
	
	private String getSql(){
		String sql  = "select * from "+
				"(select case t.flag when '99' then '20762' when '0' then '13382' when '1' then '13383' else '' end as status,"+
		        " t.plan_num,t.contract_num,t.self_num,t.contract_name,t.project_num,t.sign_cop,t.add_time,t.add_person,t.contract_money,"+
		        " t.flag pstatus,t.pinstance_id,t.model_id, id "+
		        " from ht_xx t where t.pinstance_id is not null and ((t.removed = 0 and t.flag in ('0','1')) or (t.removed=1 and t.flag ='99')) order by t.add_time desc) where 1=1 ";
		return sql;
	}
	
	private String getSqlView(String userName){
		String sql  = "select * from ("+
				"select  case t.flag when '99' then '20762' when '0' then '13382' when '1' then '13383' else '' end as status," +
				" t.plan_num,t.contract_num,t.self_num,t.contract_name,t.project_num," +
				" t.sign_cop,t.add_time,t.add_person,t.contract_money," +
				" t.flag pstatus,t.pinstance_id,t.model_id, id from ht_xx t , tasks k " +
				" where k.steplabel='备案' " +
				" and k.assignedtouser='"+userName+"'" +
				" and k.status=1 and k.incident=t.pinstance_id" +
				" and t.model_id='合同审批流程' and" +
				" t.pinstance_id is not null and " +
				" ((t.removed = 0 and t.flag ='0') or (t.removed=1 and t.flag ='99'))" +
				" order by t.add_time desc ) where 1=1 ";
		return sql;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findHtspOaByPage(int startRow,int pageSize,String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus){
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = getHtspOaFullSql(getSql(), plan_num, contract_num, self_num,
				 contract_name, project_num, sign_cop, add_time_start, add_time_end,
				 add_person, contract_money, pstatus);
		//System.out.println("sql=="+sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
		
	}
	
	public int countHtspOa(String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus){
		String sql = getHtspOaFullSql(getSql(), plan_num, contract_num, self_num,
				 contract_name, project_num, sign_cop, add_time_start, add_time_end,
				 add_person, contract_money, pstatus);
		//System.out.println("sql=="+sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num from ("+sql+") ");
		query.addScalar("count_num", Hibernate.INTEGER);
		
		return (Integer)query.uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findHtspOaViewByPage(int startRow,int pageSize,String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus,String userName){
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = getHtspOaFullSql(getSqlView(userName), plan_num, contract_num, self_num,
				 contract_name, project_num, sign_cop, add_time_start, add_time_end,
				 add_person, contract_money, pstatus);
		//System.out.println("sql=="+sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
		
	}
	
	public int countHtspOaView(String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus,String userName){
		String sql = getHtspOaFullSql(getSqlView(userName), plan_num, contract_num, self_num,
				 contract_name, project_num, sign_cop, add_time_start, add_time_end,
				 add_person, contract_money, pstatus);
		//System.out.println("sql=="+sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num from ("+sql+") ");
		query.addScalar("count_num", Hibernate.INTEGER);
		
		return (Integer)query.uniqueResult();
		
	}
	
	
	private String getHtspOaFullSql(String sql,String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus){
		if(plan_num!=null&&!"".equals(plan_num)){
			sql += " and plan_num like '%"+plan_num+"%' ";
		}
		if(contract_num!=null&&!"".equals(contract_num)){
			sql += " and contract_num like '%"+contract_num+"%' ";
		}
		if(self_num!=null&&!"".equals(self_num)){
			sql += " and self_num like '%"+self_num+"%' ";
		}
		if(contract_name!=null&&!"".equals(contract_name)){
			sql += " and contract_name like '%"+contract_name+"%' ";
		}
		if(project_num!=null&&!"".equals(project_num)){
			sql += " and project_num like '%"+project_num+"%' ";
		}
		if(sign_cop!=null&&!"".equals(sign_cop)){
			sql += " and sign_cop like '%"+sign_cop+"%' ";
		}
		if(pstatus!=null&&!"".equals(pstatus)){
			sql += " and pstatus = '"+pstatus+"' ";
		}
		if(add_time_start!=null&&!"".equals(add_time_start)){
			sql += " and add_time >= '"+add_time_start+"' ";
		}
		if(add_time_end!=null&&!"".equals(add_time_end)){
			sql += " and add_time <= '"+add_time_end+"' ";
		}
		if(add_person!=null&&!"".equals(add_person)){
			sql += " and add_person like '%"+add_person+"%' ";
		}
		if(contract_money!=null&&!"".equals(contract_money)){
			sql += " and contract_money like '%"+contract_money+"%' ";
		}
		return sql;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findHtNewByPage(int startRow,int pageSize,String plan_num,String contract_num,String self_num,
										  String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
										  String add_person,String contract_money,String contract_money_end,String pstatus){
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = "select h.plan_num,h.contract_num,h.self_num,h.contract_name,h.project_num,h.sign_cop , " +
				"h.add_time,h.add_person,h.contract_money,h.flag ,h.pinstance_id,h.model_id,h.id " +
				"from ht_new h where 1=1 and h.flag in('0','1','3')";
		if(plan_num!=null&&!"".equals(plan_num)){
			sql += " and plan_num like '%"+plan_num+"%' ";
		}
		if(contract_num!=null&&!"".equals(contract_num)){
			sql += " and contract_num like '%"+contract_num+"%' ";
		}
		if(self_num!=null&&!"".equals(self_num)){
			sql += " and self_num like '%"+self_num+"%' ";
		}
		if(contract_name!=null&&!"".equals(contract_name)){
			sql += " and contract_name like '%"+contract_name+"%' ";
		}
		if(project_num!=null&&!"".equals(project_num)){
			sql += " and project_num like '%"+project_num+"%' ";
		}
		if(sign_cop!=null&&!"".equals(sign_cop)){
			sql += " and sign_cop like '%"+sign_cop+"%' ";
		}
		if(pstatus!=null&&!"".equals(pstatus)){
			sql += " and flag = '"+pstatus+"' ";
		}
		if(add_time_start!=null&&!"".equals(add_time_start)){
			sql += " and add_time >= '"+add_time_start+"' ";
		}
		if(add_time_end!=null&&!"".equals(add_time_end)){
			sql += " and add_time <= '"+add_time_end+"' ";
		}
		if(add_person!=null&&!"".equals(add_person)){
			sql += " and add_person like '%"+add_person+"%' ";
		}
		if(contract_money!=null&&!"".equals(contract_money)){
			sql += " and contract_money like '%"+contract_money+"%' ";
		}

		sql = "select f.*,k.taskid from ("
				+ sql
				+ ")f,tasks k where f.model_id = k.processname and f.pinstance_id = k.incident and k.steplabel = 'Begin'  ";
					System.out.println(sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;

	}
	@SuppressWarnings("unchecked")
	@Override
	public int countHtNew(String plan_num,String contract_num,String self_num,
						  String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
						  String add_person,String contract_money,String contract_money_end,String pstatus){
		String sql = "select h.plan_num,h.contract_num,h.self_num,h.contract_name,h.project_num,h.sign_cop , " +
				"h.add_time,h.add_person,h.contract_money,h.flag ,h.pinstance_id,h.model_id,h.id " +
				"from ht_new h where 1=1 and h.flag in('0','1','3')";
		if(plan_num!=null&&!"".equals(plan_num)){
			sql += " and plan_num like '%"+plan_num+"%' ";
		}
		if(contract_num!=null&&!"".equals(contract_num)){
			sql += " and contract_num like '%"+contract_num+"%' ";
		}
		if(self_num!=null&&!"".equals(self_num)){
			sql += " and self_num like '%"+self_num+"%' ";
		}
		if(contract_name!=null&&!"".equals(contract_name)){
			sql += " and contract_name like '%"+contract_name+"%' ";
		}
		if(project_num!=null&&!"".equals(project_num)){
			sql += " and project_num like '%"+project_num+"%' ";
		}
		if(sign_cop!=null&&!"".equals(sign_cop)){
			sql += " and sign_cop like '%"+sign_cop+"%' ";
		}
		if(pstatus!=null&&!"".equals(pstatus)){
			sql += " and flag = '"+pstatus+"' ";
		}
		if(add_time_start!=null&&!"".equals(add_time_start)){
			sql += " and add_time >= '"+add_time_start+"' ";
		}
		if(add_time_end!=null&&!"".equals(add_time_end)){
			sql += " and add_time <= '"+add_time_end+"' ";
		}
		if(add_person!=null&&!"".equals(add_person)){
			sql += " and add_person like '%"+add_person+"%' ";
		}
		if(contract_money!=null&&!"".equals(contract_money)){
			sql += " and contract_money >= '"+contract_money+"' ";
		}
		if(contract_money!=null&&!"".equals(contract_money)){
			sql += " and contract_money like '%"+contract_money+"%' ";
		}
		sql = "select f.*,k.taskid from ("
				+ sql
				+ ")f,tasks k where f.model_id = k.processname and f.pinstance_id = k.incident and k.steplabel = 'Begin'  ";

		System.out.println("sql=="+sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num from ("+sql+") ");
		query.addScalar("count_num", Hibernate.INTEGER);

		return (Integer)query.uniqueResult();
	}


}
