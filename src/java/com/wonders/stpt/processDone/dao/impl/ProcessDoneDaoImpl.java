/**
 * 
 */
package com.wonders.stpt.processDone.dao.impl;

import com.wonders.stpt.processDone.dao.ProcessDoneDao;
import com.wonders.stpt.processDone.model.bo.ProcessDone;
import com.wonders.stpt.processDone.model.bo.TDeptContactMain;
import com.wonders.stpt.processDone.model.vo.Page;
import com.wonders.stpt.processDone.util.Constants;
import com.wonders.stpt.util.DbUtil;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


/** 
 * @ClassName: TodoItemDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午03:10:42 
 *  
 */
@Repository("processDoneDao")
public class ProcessDoneDaoImpl implements ProcessDoneDao{
	private HibernateTemplate hibernateTemplate;
	private HibernateTemplate hibernateTemplateStptdemo;
	
	@Autowired(required=false)
	private DbUtil dbUtil;
	
	public DbUtil getDbUtil() {
		return dbUtil;
	}

	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
	public ProcessDone loadById(String id){
		ProcessDone instance=null;
		List<ProcessDone> list=this.getHibernateTemplate().find("from ProcessDone a where a.id=?",new String[]{id}); 
	    if(list.size()>0)
	    {
	    	instance=(ProcessDone)list.get(0);
	    }
        return instance; 		
	}		

	public ProcessDone loadByPtypeAndPid(String ptype,String pid){
		ProcessDone instance=null;
		List<ProcessDone> list=this.getHibernateTemplate().find("from ProcessDone a where a.pid=? and a.ptype=?",new String[]{pid,ptype});
	    if(list.size()>0)
	    {
	    	instance=(ProcessDone)list.get(0);
	    }
        return instance; 		
	}

    /**
     * zhoushun 2014 返回用户信息
     * @param loginName
     * @return List
     */
    public List getUserDeptInfo(String loginName){
        final String loginNamef = loginName;
        final String sql = "select v.dept_id,v.dept_name from v_userdep v where 'ST/'||v.login_name= :loginName";
        return this.getHibernateTemplateStptdemo().executeFind(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql);
                query.setParameter("loginName",loginNamef);
                return query.list();
            }
        });
    }

	
	
	public Page findListByPage(HashMap<String, String> filter,String sortWay){
		Page page=null;
		List<ProcessDone> list = new ArrayList<ProcessDone>();	
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tran=session.beginTransaction();
		try{
			Criteria query=session.createCriteria(ProcessDone.class);
			if (!filter.isEmpty()) {
				for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {

					String paramName = i.next();

					if(paramName.equalsIgnoreCase("id")){
						query.add( Restrictions.eq(paramName, filter.get(paramName)));
						
					}else if(paramName.equalsIgnoreCase("status")){
						if(filter.get(paramName).equalsIgnoreCase("on")){
							query.add( Restrictions.in(paramName, new Object[]{Long.valueOf("1")}));
							
						}else if(filter.get(paramName).equalsIgnoreCase("off")){
							query.add( Restrictions.in(paramName, new Object[]{Long.valueOf("2"),Long.valueOf("3")}));
							
						}else{
							query.add( Restrictions.in(paramName, new Object[]{Long.valueOf(filter.get(paramName))}));
							
						}						
						
					}else if(paramName.contains("Time")){
						SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						
						String fieldName=paramName.substring(0, paramName.length()-1);
						String sDate=filter.get(paramName);
						
						boolean isStartTime=paramName.endsWith("s");				
						if(isStartTime){
							query.add( Restrictions.ge(fieldName,dateFormat.parse(sDate+" 00:00:00")));
						}else{
							query.add( Restrictions.le(fieldName,dateFormat.parse(sDate+" 23:59:59")));
						}							
												
					}else{
						if(!paramName.equalsIgnoreCase("page") && !paramName.equalsIgnoreCase("pageSize")){
							query.add( Restrictions.like(paramName, filter.get(paramName),MatchMode.ANYWHERE));
							
						}
					}

				}
			}
			
			if(sortWay.equalsIgnoreCase("asc")){
				query.addOrder( Order.asc("startTime") );
			}else{
				query.addOrder( Order.desc("startTime") );
			}
			
			int totalCount=((Integer) query.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
			query.setProjection(null);
			query.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			
			int pageNo=0;
			int pageSize=0;
			if(filter.get("page")!=null && Integer.parseInt(filter.get("page"))>0){
				pageNo=Integer.parseInt(filter.get("page"));
				pageSize=filter.get("pageSize")==null?Constants.PAGE_SIZE:Integer.parseInt(filter.get("pageSize"));
				query.setFirstResult(pageSize*(pageNo-1));
				query.setMaxResults(pageSize);
			}
			list=query.list(); 
			

			if(pageNo==0){
				page=new Page(totalCount);
			}else{
				page=new Page(totalCount,pageSize);
				page.refresh(pageNo);
			}			
			page.setResult(list);
			
			tran.commit();
		}catch(Exception e){
			tran.rollback();
			e.printStackTrace();
		}finally{
	        session.flush(); 
	        session.close();
		}
        
        return page;

	}	
	
	public List<Object> findByHQL(String hql) {
	     return this.hibernateTemplate.find(hql);
   }

	public List<Object[]> findDatasByHQL(String hql) {
		return this.hibernateTemplate.find(hql);
	}

	public void saveOrUpdateAll(Collection cols){
		hibernateTemplate.saveOrUpdateAll(cols);
	}
	
	public void saveOrUpdate(Object obj){
		hibernateTemplate.saveOrUpdate(obj);
	}
	
	public TDeptContactMain getDeptContactMainById(String id){
		TDeptContactMain instance=null;
		List<TDeptContactMain> list=this.getHibernateTemplate().find("from TDeptContactMain a where a.id=?",new String[]{id}); 
	    if(list.size()>0)
	    {
	    	instance=(TDeptContactMain)list.get(0);
	    }
        return instance;         		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findDoneUsers(String pname,String pid){
		String param1 = "";
		String param2 = "";
		String param3 = "";
		if(pname!=null&&pname.indexOf("发文流程")>-1){
			param1 = "t_doc_send";
			param2 = "modelid";
			param3 = "pinstanceid";
		}else if("收文流程".equals(pname)){
			param1 = "t_doc_receive";
			param2 = "modelid";
			param3 = "instanceid";
		}else if("工作联系单".equals(pname)){
			param1 = "t_job_contact";
			param2 = "processname";
			param3 = "instanceid";
		}else if("收呈批件".equals(pname)){
			param1 = "t_receive_directive";
			param2 = "activeid";
			param3 = "processinstanceid";
		}else if("多级工作联系单".equals(pname)){
			param1 = "t_dept_contact_main";
			param2 = "processname";
			param3 = "incidentno";
		}else if("合同审批流程".equals(pname)||"合同审批流程直接备案".equals(pname)){
			param1 = "ht_xx";
			param2 = "model_id";
			param3 = "pinstance_id";
		}else if("合同后审流程".equals(pname)){
			param1 = "t_contract_review";
			param2 = "process";
			param3 = "incident";
		}
		
		String sql = "select distinct replace(b.assignedtouser,'ST/','') login_name from "+
				" (select ts.* from "+param1+" t,tasks ts where t."+param2+" = '"+pname+"' and t."+param3+" = '"+pid+"' "+
				" and ts.processname = t."+param2+" and ts.incident = t."+param3+" "+
				" union all "+
				" select ts.* from tasks ts, "+
				" (select s.cname,s.cincident from "+param1+" t,t_subprocess s where t."+param2+" = '"+pname+"' and t."+param3+" = '"+pid+"' "+
				" and s.pname = t."+param2+" and s.pincident = t."+param3+" and s.cname != t."+param2+" "+
				" ) a where ts.processname = a.cname and ts.incident = a.cincident "+
				" ) b where b.assignedtouser != 'STPTBPMSVR_WF' and b.assignedtouser != 'SYSTEMUSER' ";
		//System.out.println(sql);
		SQLQuery query  = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("login_name", Hibernate.STRING);
		return query.list();
	}
	
	public String findTaskId(String pname,String pid){
		String sql = "select taskid from tasks where processname = '"+pname+"' and incident = "+pid+" and steplabel = 'Begin'";
		SQLQuery query  = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("taskid", Hibernate.STRING);
		List<Object> list = query.list();
		if(list!=null&&list.size()>0){
			return (String) list.get(0);
		}
		return null;
	}
	
	public String findAdderDept(String pname,String pid){
		String sql = "select cs.name from cs_organ_node cs where cs.id = "+
				" (select substr(tt.helpurl,instr(tt.helpurl,':')+1,instr(tt.helpurl,'<')-instr(tt.helpurl,':')-1)"+
				"  from tasks tt where tt.processname = '"+pname+"' and tt.incident = "+pid+" and tt.steplabel = 'Begin')";
		SQLQuery query  = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return String.valueOf(query.addScalar("name", Hibernate.STRING).uniqueResult());
	}
	
	public String findAdderName(String pname,String pid){
		String sql = "select * from ("+
				" select name from cs_user c where c.login_name = "+
				" (select replace(s.assignedtouser,'ST/','') "+
				" from tasks s where s.processname = '"+pname+"' and s.steplabel = 'Begin' and s.incident = '"+pid+"')" +
				") where rownum<2";
		SQLQuery query  = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return String.valueOf(query.addScalar("name", Hibernate.STRING).uniqueResult());
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public HibernateTemplate getHibernateTemplateStptdemo() {
		return hibernateTemplateStptdemo;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplateStptdemo(HibernateTemplate hibernateTemplateStptdemo) {
		this.hibernateTemplateStptdemo = hibernateTemplateStptdemo;
	}
	
	public List<Object[]> findProjectAssets(String asset_ids){
		String[] asset_id_split = asset_ids.split(",");
		String sql = "";
		Session session = hibernateTemplateStptdemo.getSessionFactory().getCurrentSession();
		for(int i=0;i<asset_id_split.length;i++){
			if(i>0){
				sql += " union all ";
			}
			sql += "select t.id,t.asset_name,t.use_time,t.use_life,t.shelf_life,t.asset_id from t_asset_info t where t.asset_id =  "+asset_id_split[i]+"  and t.removed = '0'";
		}
		//System.out.println("sql======"+sql);
		Query query = session.createSQLQuery(sql).addScalar("id", Hibernate.INTEGER).addScalar("asset_name", Hibernate.STRING).addScalar("use_time", Hibernate.STRING).addScalar("use_life", Hibernate.STRING).addScalar("shelf_life", Hibernate.STRING).addScalar("asset_id", Hibernate.STRING);
		List<Object[]> list = query.list();
		return list;
	}
	
	public String findNameByLoginName(String login_name){
		String sql = "select name from cs_user where login_name = '"+login_name+"'";
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql).addScalar("name", Hibernate.STRING);
		return (String)query.uniqueResult();
	}
	
	public void update(Object obj){
		hibernateTemplateStptdemo.update(obj);
	}
	
	public List<Object> findByHQLFromStptdemo(String hql) {
	     return this.hibernateTemplateStptdemo.find(hql);
  }
	
	@SuppressWarnings("unchecked")
	public List<Object> queryForList(String sql,Class e){
		return this.dbUtil.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(e));
		//return this.jdbcTemplate.queryForList(sql, DocFile.class);
	}
	
	public List<Object[]> findAllDocSendType(){
		String sql = "select data_type from dw_data_exchange_user t where data_type like 'docSend%' and user_name = 'wonder'";
		Session session = hibernateTemplateStptdemo.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql).addScalar("data_type", Hibernate.STRING);
		List<Object[]> list = query.list();
		return list;
	}

	@Override
	public String findProjectStatus(String incident, String processname) {
		String sql = "select case s.status when 1 then '进行中' when 2 then '已完成' end as status from incidents s " +
				"where s.incident = :incident and s.processname = :processname and (s.status = 1 or s.status = 2) ";
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql).addScalar("status", Hibernate.STRING)
			.setParameter("incident", incident)
			.setParameter("processname", processname);
		return (String)query.uniqueResult();
	}
	
	public List<Object[]> findCodeIds(){
		String sql = "select t.id,t.new_dept_id from t_dept_code t where t.removed = '0'";
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql).addScalar("id", Hibernate.STRING).addScalar("new_dept_id", Hibernate.STRING);
		List<Object[]> list = query.list();
		return list;
	}
}
