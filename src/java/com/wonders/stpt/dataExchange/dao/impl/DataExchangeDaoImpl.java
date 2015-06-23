/** 
* @Title: DataExchangeDaoImpl.java 
* @Package com.wonders.stpt.dataExchange.dao.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com 
* @date 2013-8-7 下午03:15:31 
* @version V1.0 
*/
package com.wonders.stpt.dataExchange.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.wonders.stpt.util.SpringBeanUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.dataExchange.dao.DataExchangeDao;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeStore;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeUser;
import com.wonders.stpt.processDone.model.vo.Page;
import com.wonders.stpt.processDone.util.Constants;


/** 
 * @ClassName: DataExchangeDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lushuaifeng 
 * @date 2013-8-7 下午03:15:31 
 *  
 */
@Repository("dataExchangeDao")
public class DataExchangeDaoImpl implements DataExchangeDao{
	private HibernateTemplate hibernateTemplate;
	private HibernateTemplate hibernateTemplate2;	

	public DwDataExchangeStore loadById(java.lang.String id) {
		DwDataExchangeStore instance=null;
		List<DwDataExchangeStore> list=this.getHibernateTemplate().find("from DwDataExchangeStore a where a.id=?",id); 
	    for(int i=0;i<list.size();i++)
	    {
	    	instance=(DwDataExchangeStore)list.get(i);
	    }
        return instance;
	}
	
	public Page findListByPage(HashMap<String, String> filter,String sortWay){
		Page page=null;
		List<DwDataExchangeStore> list = new ArrayList<DwDataExchangeStore>();	
		Session session=this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tran=session.beginTransaction();
		try{
			Criteria query=session.createCriteria(DwDataExchangeStore.class);			
			if (!filter.isEmpty()) {
				for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {

					String paramName = i.next();

					if(paramName.equalsIgnoreCase("id")||paramName.equalsIgnoreCase("dataType")){
						query.add( Restrictions.eq(paramName, filter.get(paramName)));
						
					}else if(paramName.equalsIgnoreCase("valid")){
						query.add( Restrictions.eq(paramName, Long.parseLong(filter.get(paramName))));
						
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
												
					}else if(paramName.contains("Date")){						
						String fieldName=paramName.substring(0, paramName.length()-1);
						String sDate=filter.get(paramName);
						
						boolean isStartTime=paramName.endsWith("s");				
						if(isStartTime){
							query.add( Restrictions.ge(fieldName,sDate));
						}else{
							query.add( Restrictions.le(fieldName,sDate));
						}							
												
					}else{
						if(!paramName.equalsIgnoreCase("page") && !paramName.equalsIgnoreCase("pageSize")){
							if(paramName.equals("content2")){
								query.add( Restrictions.like("content", filter.get(paramName),MatchMode.ANYWHERE));
							}else if("contents".equals(paramName)){
                                String[] contents  = filter.get(paramName).split(",");
                                if(contents.length == 0){
                                    query.add( Restrictions.like(paramName,contents[0],MatchMode.ANYWHERE));
                                }else{
                                    Disjunction disjunction = Restrictions.disjunction();
                                    for (String content : contents) {
                                        disjunction.add(Restrictions.like("content",content,MatchMode.ANYWHERE));
                                    }
                                    query.add(disjunction);
                                }
                            }
                            else{
								query.add( Restrictions.like(paramName, filter.get(paramName),MatchMode.ANYWHERE));
							}
							
						}
					}

				}
			}
			
			if(sortWay.equalsIgnoreCase("asc")){
				query.addOrder( Order.asc("createTime") );
			}else{
				query.addOrder( Order.desc("createTime") );
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
	
	public void update(DwDataExchangeStore transientInstance) {
		getHibernateTemplate().update(transientInstance);
}	
	
	public List<DwDataExchangeUser> findSetUsers() {
    	List<DwDataExchangeUser> list=this.getHibernateTemplate().find("from DwDataExchangeUser a where a.portName like '%set%' and a.removed=0");        
        return list;

	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public HibernateTemplate getHibernateTemplate2() {
		return hibernateTemplate2;
	}
	
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate2(HibernateTemplate hibernateTemplate2) {
		this.hibernateTemplate2 = hibernateTemplate2;
	}
	
	public boolean ifReceiver(String stepName,String pname,String loginName,String deptId,String userId){//
		String sql = "select username from t_flowsetp_config t where t.stepname = '"+stepName+"' and t.processname = '"+pname+"' and t.parent_dept = '"+deptId+"'";
		String username = getStringResult(sql);
		if(loginName!=null&&loginName.equals(username)){
			return true;
		}else if(username==null||"null".equals(username)){
			sql = "select count(*) count_num from v_dept_receivers t where t.id = '"+userId+"' and t.dept_id = "+deptId;
			int count_num = getUniqueResult(sql);
			if(count_num>0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}	
	
	private int getUniqueResult(String sql){
		int s=0;
		Session session=this.getHibernateTemplate2().getSessionFactory().openSession();
		Transaction tran=session.beginTransaction();
		try{
			SQLQuery query=session.createSQLQuery(sql);
			s=(Integer)query.addScalar("count_num", Hibernate.INTEGER).uniqueResult();
			tran.commit();
		}catch(Exception e){
			tran.rollback();
			//e.printStackTrace();
		}finally{			
	        session.flush(); 
	        session.close();
		}
        return s;
	}
	
	private String getStringResult(String sql){
		String s = "";
		Session session=this.getHibernateTemplate2().getSessionFactory().openSession();
		Transaction tran=session.beginTransaction();
		try{
			SQLQuery query=session.createSQLQuery(sql);
			s=(String)query.addScalar("username", Hibernate.STRING).uniqueResult();
			tran.commit();
		}catch(Exception e){
			tran.rollback();
			//e.printStackTrace();
		}finally{			
	        session.flush(); 
	        session.close();
		}
        return s;
	}
	
	
	
	public void save(Object obj){
		hibernateTemplate.save(obj);
	}
	
	public HashMap<String,String> getEamProjectReportInfo(String loginName){
		String sql = "select login_name as loginName,parent_node_id as deptId from v_userdep t where t.login_name ='"+loginName
                +"' and (t.parent_node_id between 10215 and 10219 or t.parent_node_id between 10114 and 10118)";
		List<HashMap<String,String>> rs=getReportInfoResult(sql);
		if(rs==null||rs.size()==0){
			return null;
		}else{
			return rs.get(0);
		}
	}
	
	
	public List<HashMap<String,String>> getReportInfoResult(String sql){
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();		
		Session session=this.getHibernateTemplate2().getSessionFactory().openSession();
		Transaction tran=session.beginTransaction();
		try{
			SQLQuery query=session.createSQLQuery(sql);
			query.addScalar("loginName",Hibernate.STRING);
			query.addScalar("deptId",Hibernate.STRING);
			list =query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			tran.commit();
		}catch(Exception e){
			tran.rollback();
			//e.printStackTrace();
		}finally{
			
	        session.flush(); 
	        session.close();
		}		
		return list;
	}	
	
	public List<HashMap<String,String>> getDeptLeaderName(String oldDeptId){
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		String sql = "select u.login_name as loginName,u.name from cs_user u,cs_group g,cs_user_group c where c.security_group_id=g.id and g.code='bumenlingdao' and u.id=c.security_user_id and c.department_id='"+oldDeptId+"'";
		Session session=this.getHibernateTemplate2().getSessionFactory().openSession();
		Transaction tran=session.beginTransaction();
		try{
			SQLQuery query=session.createSQLQuery(sql);
			query.addScalar("loginName",Hibernate.STRING);
			query.addScalar("name",Hibernate.STRING);
			list =query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			tran.commit();
		}catch(Exception e){
			tran.rollback();
			//e.printStackTrace();
		}finally{			
	        session.flush(); 
	        session.close();
		}		
		return list;
	}
	
	
}
