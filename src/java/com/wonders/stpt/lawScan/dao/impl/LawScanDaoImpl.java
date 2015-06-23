package com.wonders.stpt.lawScan.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.lawScan.dao.LawScanDao;

@Repository("lawScanDao")
public class LawScanDaoImpl implements LawScanDao{
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public List<Object[]> findLawScanContractList(int startRow, int pageSize, 
			String plan_num, String contract_num, String self_num, String contract_name,
			String project_num, String sign_cop, String add_time_start, String add_time_end,
			String add_person, String contract_money, String pstatus,String ifRead){
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = "select * " +
				getSql(ifRead);
		sql = getFullSql(sql, plan_num, contract_num, self_num, 
			      contract_name, project_num, sign_cop, add_time_start, add_time_end, 
			      add_person, contract_money, pstatus);
		if("yes".equals(ifRead)){
			sql = sql + " order by read_date desc";
		}else{
			sql = sql + " order by occurtime desc";
		}
		//System.out.println(sql);
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
	    return list;
	}
	
	public int countLawScanContract(String plan_num, String contract_num, String self_num,
			String contract_name, String project_num, String sign_cop, String add_time_start, 
			String add_time_end, String add_person, String contract_money, String pstatus,String ifRead){
	    String sql = "select count (*) count_num "+getSql(ifRead);
	    sql = getFullSql(sql, plan_num, contract_num, self_num, 
			      contract_name, project_num, sign_cop, add_time_start, add_time_end, 
			      add_person, contract_money, pstatus);
	    //System.out.println(sql);
	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    query.addScalar("count_num", Hibernate.INTEGER);

	    return ((Integer)query.uniqueResult()).intValue();
	  }
	
	public String getFullSql(String sql, String plan_num, String contract_num, String self_num, String contract_name, String project_num, String sign_cop, String add_time_start, String add_time_end, String add_person, String contract_money, String pstatus)
	  {
	    if ((plan_num != null) && (!"".equals(plan_num))) {
	      sql = sql + " and plan_num like '%" + plan_num + "%' ";
	    }
	    if ((contract_num != null) && (!"".equals(contract_num))) {
	      sql = sql + " and contract_num like '%" + contract_num + "%' ";
	    }
	    if ((self_num != null) && (!"".equals(self_num))) {
	      sql = sql + " and self_num like '%" + self_num + "%' ";
	    }
	    if ((contract_name != null) && (!"".equals(contract_name))) {
	      sql = sql + " and contract_name like '%" + contract_name + "%' ";
	    }
	    if ((project_num != null) && (!"".equals(project_num))) {
	      sql = sql + " and project_num like '%" + project_num + "%' ";
	    }
	    if ((sign_cop != null) && (!"".equals(sign_cop))) {
	      sql = sql + " and sign_cop like '%" + sign_cop + "%' ";
	    }
	    if ((pstatus != null) && (!"".equals(pstatus))) {
	      sql = sql + " and pstatus = '" + pstatus + "' ";
	    }
	    if ((add_time_start != null) && (!"".equals(add_time_start))) {
	      sql = sql + " and add_time >= '" + add_time_start + "' ";
	    }
	    if ((add_time_end != null) && (!"".equals(add_time_end))) {
	      sql = sql + " and add_time <= '" + add_time_end + "' ";
	    }
	    if ((add_person != null) && (!"".equals(add_person))) {
	      sql = sql + " and add_person like '%" + add_person + "%' ";
	    }
	    if ((contract_money != null) && (!"".equals(contract_money))) {
	      sql = sql + " and contract_money like '%" + contract_money + "%' ";
	    }
	    return sql;
	  }
	
	String getSql(String ifRead){
		String sql = "";
		if("yes".equals(ifRead)){
			sql = " from (select case h.flag  when '0' then '13382' when '1' then '13383' else '' end as status," +
			" h.plan_num,h.contract_num,h.self_num,h.contract_name,h.project_num,h.sign_cop,h.add_time," +
			"h.add_person,h.contract_money, h.flag pstatus,h.pinstance_id,h.model_id,k.taskid,k.read_date " +
			" from ht_xx h," +
			"(select p.pname,p.pid,t.taskid,to_char(p.read_date,'yyyy-mm-dd hh24:mi:ss') read_date from law_scan p,tasks t where p.pname = t.processname and p.pid = t.incident and t.steplabel = 'Begin') k " +
			" where k.pname = h.model_id and k.pid = h.pinstance_id and h.removed = 0 ) where 1=1 ";
		}else{
			sql = " from (select case h.flag  when '0' then '13382' when '1' then '13383' else '' end as status," +
			" h.plan_num,h.contract_num,h.self_num,h.contract_name,h.project_num,h.sign_cop,h.add_time," +
			"h.add_person,h.contract_money, h.flag pstatus,h.pinstance_id,h.model_id,v.taskid,v.cname,v.cincident,v.occurtime " +
			" from ht_xx h," +
			"(select  "+
			" part.deptId as deptId, " +
			"'' userId, " +
			"'controller' app,  " +
			" inci.summary title, "+
			" 'ST/*FLZP' as loginName,"+
			" part.steplabel stepName, "+
			" part.endtime as occurTime," +
			" part.pname typename, "+
			" part.pname pname, " +
			" part.pincident pincident, " +
			" part.cname cname, " +	
			" part.cincident cincident, " +
			" part.taskid taskId ,"+
			" '0' taskType, "+
			" '' dbxType, "+
			" inci.initiator,"+
			" '' initdept"+
	    	" from incidents inci,"+
	    	" (" +
	    	
	    	"(select " +
	    	" substr(t.helpurl,instr(t.helpurl,':')+1,instr(t.helpurl,'<+>')-instr(t.helpurl,':')-1)" +
			" as deptId ," +
	    	"t.processname as pname, "+
	    	" to_char(t.incident) as pincident, "+
	    	" t.processname as cname, "+
	    	" to_char(t.incident) as cincident, "+
	    	" t.steplabel, "+
	    	" t.overduetime, "+
	    	" to_char(t.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime, "+
	    	" t.taskid, "+
	    	" t.assignedtouser "+
	    	" from tasks t "+
	    	" where exists (select '' "+
	    	" from processes a "+
	    	" where t.processname = a.processname "+
	    	" and a.launchtype = 0 "+
	    	" and a.processname <> '拟办子流程' "+
	    	" and a.processname <> '办结子流程' "+
	    	" ) "+
	    	//待修改 新发文流程
	    	" and t.processname='合同审批流程'"+
	    	" and t.status = 1 "+
	    	" and t.assignedtouser = 'ST/*FLZP' "+
	    	
	    	" ) " +
	    	
	    	"union "+
	    	
	    	" (select " +
	    	" substr(t.helpurl,instr(t.helpurl,':')+1,instr(t.helpurl,'<+>')-instr(t.helpurl,':')-1)" +
			" as deptId ," +
	    	" b.pname as pname, "+
	    	" b.pincident as pincident, "+
	    	" b.cname as cname, "+
	    	" b.cincident as cincident, "+
	    	" t.steplabel, "+
	    	" t.overduetime, "+
	    	" to_char(t.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime, "+
	    	" t.taskid, "+
	    	" t.assignedtouser "+
	    	" from t_subprocess b, tasks t "+
	    	" where b.cname = t.processname " +
	    	
	    	//待修改 新发文流程
	    	" and b.pname='合同审批流程'"+
	    	" and b.cincident = t.incident "+
	    	" and not exists "+
	    	" (select '' "+
	    	" from processes a "+
	    	" where t.processname = a.processname "+
	    	" and a.launchtype = 0 "+
	    	" and a.processname <> '拟办子流程' "+
	    	" and a.processname <> '办结子流程' "+
	    	" ) "+
	    	" and t.status = 1 "+
	    	" and t.assignedtouser = 'ST/*FLZP' "+
	    	" )" +
	    	
	    	") part "+
	    	
	    	" where part.pname = inci.processname "+
	    	" and part.pincident = inci.incident" +
	    	") v where v.pincident = h.pinstance_id and v.pname = h.model_id and h.removed = 0" +
	    	" and not exists (select '' from law_scan f where f.cname = v.cname and f.cid = v.cincident) " +
	    	") where 1=1 ";
		}
		return sql;
	}
	
	public void save(Object obj){
		this.hibernateTemplate.save(obj);
	}
	
	public boolean findIfExists(String cname,String cid){
		String sql = "select count(*) count_num from law_scan where cname = ? and cid = ?";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, cname);
		query.setString(1, cid);
	    query.addScalar("count_num", Hibernate.INTEGER);
	    if(((Integer)query.uniqueResult()).intValue()>0){
	    	return true;
	    }else{
	    	return false;
	    }
	}
}
