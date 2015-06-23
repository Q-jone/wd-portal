package com.wonders.stpt.build.dao.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.build.dao.BuildDao;

@Repository("buildDao")
public class BuildDaoImpl implements BuildDao{
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProcessData(){
		String sql = "select sum(t.done_total) done_sum,sum(t.doing_total) doing_sum,sum(t.waiting_total) waiting_sum,sum(t.abnormal_total) abnormal_sum "+
				" from dw_build_project_info t where t.operatetime = to_char(sysdate,'yyyy-mm-dd')";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProcessList(String processType){
		String sql = "";
		String create_time = getLatestDate();
		if("2".equals(processType)){
			sql = "select t.company_id,s.unit_name,sum(t.milestone_delay_total) milestone_delay_sum,sum(t.milestone_total) milestone_sum,"+
					" sum(t.abnormal_total) abnormal_sum,sum(t.plan_total) plan_sum "+
					" from dw_build_project_info t ,greata_construction_unit s "+
					" where s.unit_id = t.company_id and t.operatetime = ?"+
					" group by t.company_id,s.unit_name "+
					" order by abnormal_sum desc,milestone_delay_sum desc";
		}else{	
			sql = "select t.project_id,s.project_name,sum(t.milestone_delay_total) milestone_delay_sum,sum(t.milestone_total) milestone_sum,"+
					"sum(t.abnormal_total) abnormal_sum,sum(t.plan_total) plan_sum "+
					" from dw_build_project_info t ,greata_project s "+
					" where s.project_id = t.project_id and t.operatetime = ?"+
					" group by t.project_id,s.project_name "+
					" order by abnormal_sum desc,milestone_delay_sum desc";
		}
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.setString(0,create_time ).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProcessDetailList(String processType,String project_id,String detailType){
		String sql = "";
		String name = "";
		String create_time = getLatestDate();
		if("2".equals(processType)){
			name = "company_id";
		}else{
			name = "project_id";
		}
		if("1".equals(detailType)){
//			sql = " select s.plan_type_name,sum(t.plan_total) plan_sum,sum(t.begin_delay_total) begin_delay_sum,"+
//				 " sum(t.finish_delay_total) finish_delay_sum,sum(t.waiting_total) waiting_sum,"+
//				 " sum(t.doing_total) doing_sum,sum(t.done_total) done_sum "+
//				 " from dw_build_project_info t,greata_plan_type s,greata_plan_task r "+
//				 " where s.plan_type_id = t.plan_id and t."+name+" = ? and t.operatetime = to_char(sysdate,'yyyy-mm-dd')"+
//				 " and r.plan_id = s.plan_id and t.project_id = r.project_id "+
//				 " group by s.plan_type_name";
//			
			sql = "select v.plan_type_name,v.plan_type_id,sum(t.plan_total) plan_sum,sum(t.begin_delay_total) begin_delay_sum,"+
					" sum(t.finish_delay_total) finish_delay_sum,sum(t.waiting_total) waiting_sum,"+
					" sum(t.doing_total) doing_sum,sum(t.done_total) done_sum "+
					" from dw_build_project_info  t,(select distinct s.plan_type_name,s.plan_type_id from greata_plan_type s) v "+
					" where v.plan_type_id = t.plan_id and t.operatetime = ? and t."+name+" = ? "+
					" group by v.plan_type_name,v.plan_type_id";
		}else if("2".equals(detailType)){
//			sql = "select s.single_name,sum(t.plan_total) plan_sum,sum(t.begin_delay_total) begin_delay_sum,"+
//				 " sum(t.finish_delay_total) finish_delay_sum,sum(t.waiting_total) waiting_sum,"+
//				 " sum(t.doing_total) doing_sum,sum(t.done_total) done_sum "+
//				 " from dw_build_project_info t,greata_single s,greata_plan_task r "+
//				 " where s.single_id = t.single_id and t."+name+" = ? and t.operatetime = to_char(sysdate,'yyyy-mm-dd')"+
//				 " and r.plan_id = s.plan_id and t.project_id = r.project_id "+
//				 " group by s.single_name";
			
			sql = "select v.single_name,v.single_id,sum(t.plan_total) plan_sum,sum(t.begin_delay_total) begin_delay_sum,"+
					" sum(t.finish_delay_total) finish_delay_sum,sum(t.waiting_total) waiting_sum,"+
					" sum(t.doing_total) doing_sum,sum(t.done_total) done_sum "+
					" from dw_build_project_info  t,(select distinct s.single_name,s.single_id from greata_single s) v "+
					" where v.single_id = t.single_id and t.operatetime = ? and t."+name+" = ? "+
					" group by v.single_name,v.single_id";
		}else if("3".equals(detailType)){
			sql = "select t.plan_year,t.plan_month,"+
				" sum(t.begin_plan) begin_plan_sum,sum(t.begin_real) begin_real_sum,sum(t.begin_delay) begin_delay_sum,"+
				" sum(t.finish_plan) finish_plan_sum,sum(t.finish_real) finish_real_sum,sum(t.finish_delay) finish_delay_sum "+
				" from dw_build_project_info_process t "+
				" where t.operatetime = ? "+
				" and t."+name+" = ? "+
				" group by t.plan_year,t.plan_month "+
				" order by t.plan_year desc,t.plan_month";
		}
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.setString(0, create_time).setString(1, project_id).list();
	}
	
	public List<Object[]> getTasksList(String project_id,String plan_id,String processType,String detailType,String type){
		String table1 = "";
		String table2 = "";
		String param1 = "";
		String param2 = "";
		String param3 = "";
		String param4 = "";
		String param5 = "";
		if("1".equals(detailType)){
			table2 = "greata_plan_type";
			param1 = "plan_type_name";
			param2 = "plan_type_id";
		}else if("2".equals(detailType)){
			table2 = "greata_single";
			param1 = "single_name";
			param2 = "single_id";
		}
		if("1".equals(processType)){
			table1 = "greata_project";
			param3 = "project_name";
			param4 = "project_id";
			param5 = "project_id";
		}else if("2".equals(processType)){
			table1 = "greata_construction_unit";
			param3 = "unit_name";
			param4 = "unit_id";
			param5 = "plan_id";
		}
		
		String sql = "select t.plan_name,s."+param3+",r."+param1+"," +
			"t.plan_begin_time,t.plan_finish_time,t.real_begin_time,t.real_finish_time "+
			" from greata_plan_task t,"+table1+" s,"+table2+" r "+
			" where s."+param5+" = t."+param5+" and s."+param4+" = ? "+ 
			" and r.plan_id = t.plan_id and r."+param2+" = ? "+
			" and t.create_time = (select max(create_time) from greata_plan_task) and t.plan_type = '3' ";
		if("startDelay".equals(type)){
			sql = sql+ " and ((t.real_begin_time is null and t.plan_begin_time<to_char(sysdate,'yyyy-mm-dd'))"+
				   " or (t.real_begin_time is not null and t.plan_begin_time<t.real_begin_time))";
		}else if("finishDelay".equals(type)){
			sql = sql+ " and ((t.real_finish_time is null and t.plan_finish_time<to_char(sysdate,'yyyy-mm-dd'))"+
			   	   " or (t.real_finish_time is not null and t.plan_finish_time<t.real_finish_time))";
		}
		//System.out.println(sql);
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.setString(0, project_id).setString(1, plan_id).list();
	}
	
	private String getLatestDate(){
		String sql = "select max(t.operatetime) create_time from dw_build_project_info t";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		String create_time = (String)query.uniqueResult();
		if(create_time==null||create_time.length()==0||"null".equals(create_time)){
			create_time = "2000-01-01";
		}
		return create_time;
	}
	
	public List<Object[]> getProcessYearCompareList(String year,String project_name,String type_name){
		String sql = "select * from dw_build_plantask_year where stat_year = ? and operatetime = (select max(operatetime) from dw_build_plantask_year) ";
		if(project_name!=null&&project_name.length()>0){
			sql += " and project_name like '%"+project_name+"%' ";
		}
		if(type_name!=null&&type_name.length()>0){
			sql += " and type_name like '%"+type_name+"%' ";
		}
		sql += " order by project_id ";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.setString(0, year).list();
	}
	
	public List<Object[]> getProcessMonthCompareList(String year,String stat_month,String type_name,String project_name){
		String sql = "select * from dw_build_plantask_month where stat_year = ? and operatetime = (select max(operatetime) from dw_build_plantask_month) ";
		if(stat_month!=null&&stat_month.length()>0){
			sql += " and stat_month like '%"+stat_month+"%' ";
		}
		if(type_name!=null&&type_name.length()>0){
			sql += " and type_name like '%"+type_name+"%' ";
		}
		if(project_name!=null&&project_name.length()>0){
			sql += " and project_name like '%"+project_name+"%' ";
		}
		sql += " order by stat_month,type_id,project_id ";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.setString(0, year).list();
	}
	
	public List getListBySql(String sql){
		//String sql = "select distinct stat_year from dw_build_plantask_year t";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findProcessPlanComparision(String projectId,String dateType){
		DateFormat df1 = new SimpleDateFormat("yyyy");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM");
		String dateString = "";
		if("year".equals(dateType)){
			dateString = df1.format(new Date());
		}else if("month".equals(dateType)){
			dateString = df2.format(new Date());
		}
		String sql = "select v.plan_name,v.delay_days,v.real_finish_time,v.plan_days,v.left_days,"+
			" (case when v.plan_days = 0 then 0 else round((v.plan_days-v.left_days)*100/v.plan_days,2) end)||'%' work_persent,"+
			" v.plan_completion,v.total_completion,v.progress_persent||'%',"+
			" v.progress_persent-(case when v.plan_days = 0 then 0 else round((v.plan_days-v.left_days)*100/v.plan_days,2) end) difference,v.lev,v.plan_finish_time,v.plan_id,v.path from ( "+
			" select sys_connect_by_path(t.plan_id,';') path,t.plan_id,t.plan_finish_time,t.plan_name,case when t.real_begin_time is not null then to_date(t.real_begin_time,'yyyy-mm-dd') - to_date(t.plan_begin_time,'yyyy-mm-dd') "+ 
			" else to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') - to_date(t.plan_begin_time,'yyyy-mm-dd') end delay_days,"+
			" t.real_finish_time,to_date(t.plan_finish_time,'yyyy-mm-dd') - to_date(t.plan_begin_time,'yyyy-mm-dd') plan_days,"+
			" case when to_char(sysdate,'yyyy-mm-dd')>t.plan_begin_time and t.real_finish_time is null "+
			" then to_date(t.plan_finish_time,'yyyy-mm-dd') - to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') "+
			" when t.real_finish_time is not null then to_date(t.plan_finish_time,'yyyy-mm-dd') - to_date(t.real_finish_time,'yyyy-mm-dd') "+
			" else to_date(t.plan_finish_time,'yyyy-mm-dd') - to_date(t.plan_begin_time,'yyyy-mm-dd') end left_days,"+
			" t.plan_completion,t.total_completion,case when t.plan_completion = 0 then 0 else round(to_number(t.total_completion)*100/to_number(t.plan_completion),2) end progress_persent, " +
			" level lev from greata_plan_task t where removed = '0' and project_id = ? "+//addStr+
			" start with  parent_plan_id = 'root' connect by  prior plan_id = parent_plan_id) v";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<Object[]> list = query.setString(0, projectId).list();
		
		if(!"".equals(dateString)){
			Map<String,String> rec = new HashMap<String,String>();
			for(int i=list.size()-1;i>=0;i--){
				Object[] o = list.get(i);
				BigDecimal lev = (BigDecimal)o[10];
				String planFinishTime = (String)o[11];
				String planId = (String)o[12];
				boolean inRange = (planFinishTime != null && planFinishTime.toString().contains(dateString));
				if(!rec.containsKey(planId)){
					if(inRange){
						String path = (String)o[13];
						String[] parentIds = path.substring(1).split(";");
						for(String pid : parentIds){
							rec.put(pid, null);						
						}
					}else{
						list.remove(i);
					}
				}
			}
		}
		return list;
	}
}
