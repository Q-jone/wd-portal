/**
 * 
 */
package com.wonders.stpt.applyProject.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.applyProject.dao.ApplyProjectDao;
import com.wonders.stpt.applyProject.model.bo.TApplyProject;
import com.wonders.stpt.applyProject.model.vo.ApplyProjectListVo;
import com.wonders.stpt.applyProject.util.ApplyProjectUtil;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;

/** 
 * @ClassName: ApplyProjectDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月30日 上午10:39:48 
 *  
 */

@Repository("applyProjectDao")
public class ApplyProjectDaoImpl implements ApplyProjectDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public String save(TApplyProject bo){
		this.getHibernateTemplate().save(bo);
		return bo.getId();
	}
	public String update(TApplyProject bo){
		this.getHibernateTemplate().update(bo);
		return bo.getId();
	}
	
	public TApplyProject load(String id){
//		TBidPlan t = this.getHibernateTemplate().load(TApplyProject.class, id);
//		if(!Hibernate.isInitialized(t)) 
//			Hibernate.initialize(t); 
		
		TApplyProject t = this.getHibernateTemplate().get(TApplyProject.class, id);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public PageResultSet<Map<String,Object>> list(ApplyProjectListVo vo){
		Map<String,Object> map = ApplyProjectUtil.generateFilterMap(vo);
		List<Object> src = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from V_APPLY_PROJECT t where 1=1 and removed='0' ");
		String order = "";
		for(Map.Entry<String, Object> entry : map.entrySet()){
			if(entry.getKey().indexOf("_equal") > 0){
				sql.append(" and t."+entry.getKey().replace("_equal", "")+" = ? ");
				src.add(entry.getValue());
			}else if(entry.getKey().indexOf("_like") > 0){
				sql.append(" and t."+entry.getKey().
						replace("_like", "")
						+" like ? escape '/'");
				src.add("%"+entry.getValue().toString().replaceAll("%","/%").replaceAll("_","/_")+"%");
			}else if(entry.getKey().indexOf("_startDate") > 0){
					sql.append(" and t."+entry.getKey().replace("_startDate", "")+" >= ? ");
					src.add(entry.getValue());
			}else if(entry.getKey().indexOf("_endDate") > 0){
				sql.append(" and t."+entry.getKey().replace("_endDate", "")+" <= ? ");
				src.add(entry.getValue());
			}else if("order".equals(entry.getKey())){
				order = " order by t." + entry.getValue() +" desc";
			}
		}
		sql.append(order);
		PageResultSet<Map<String,Object>> result = new PageResultSet<Map<String,Object>>();
		Query query = this.getHibernateTemplate().getSessionFactory().
		getCurrentSession().createSQLQuery(sql.toString()).
		setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for(int i=0;i<src.size();i++){
			query.setParameter(i, src.get(i));
		}
		int totalRows = query.list().size();
		PageInfo pageinfo = new PageInfo(totalRows, ApplyProjectUtil.getPageSize(vo.pageSize), ApplyProjectUtil.getPage(vo.page));	
		query.setFirstResult(pageinfo.getBeginIndex());
		query.setMaxResults(pageinfo.getPageSize());
		List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		result.setList(list);
		result.setPageInfo(pageinfo);
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public PageResultSet<Map<String,Object>> listAll(ApplyProjectListVo vo){
		Map<String,Object> map = ApplyProjectUtil.generateFilterMap(vo);
		List<Object> src = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from V_APPLY_PROJECT t where 1=1 and removed='0' ");
		String order = "";
		for(Map.Entry<String, Object> entry : map.entrySet()){
			if(entry.getKey().indexOf("_equal") > 0){
				sql.append(" and t."+entry.getKey().replace("_equal", "")+" = ? ");
				src.add(entry.getValue());
			}else if(entry.getKey().indexOf("_like") > 0){
				sql.append(" and t."+entry.getKey().
						replace("_like", "")
						+" like ? escape '/'");
				src.add("%"+entry.getValue().toString().replaceAll("%","/%").replaceAll("_","/_")+"%");
			}else if(entry.getKey().indexOf("_startDate") > 0){
					sql.append(" and t."+entry.getKey().replace("_startDate", "")+" >= ? ");
					src.add(entry.getValue());
			}else if(entry.getKey().indexOf("_endDate") > 0){
				sql.append(" and t."+entry.getKey().replace("_endDate", "")+" <= ? ");
				src.add(entry.getValue());
			}else if("order".equals(entry.getKey())){
				order = " order by t." + entry.getValue() +" desc";
			}
		}
		sql.append(order);
		PageResultSet<Map<String,Object>> result = new PageResultSet<Map<String,Object>>();
		Query query = this.getHibernateTemplate().getSessionFactory().
		getCurrentSession().createSQLQuery(sql.toString()).
		setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for(int i=0;i<src.size();i++){
			query.setParameter(i, src.get(i));
		}
		int totalRows = query.list().size();
		PageInfo pageinfo = new PageInfo(totalRows, totalRows==0?10:totalRows, 1);	
		
		List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		result.setList(list);
		result.setPageInfo(pageinfo);
		return result;
	}
	
}
