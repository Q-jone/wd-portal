/**
 * 
 */
package com.wonders.stpt.innerWork.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.innerWork.dao.InnerWorkDao;
import com.wonders.stpt.innerWork.model.bo.TInnerWork;
import com.wonders.stpt.innerWork.model.vo.InnerWorkListVo;
import com.wonders.stpt.innerWork.util.InnerWorkUtil;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;

/** 
 * @ClassName: InnerWorkDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月7日 下午7:26:48 
 *  
 */
@Repository("innerWorkDao")
public class InnerWorkDaoImpl implements InnerWorkDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public String save(TInnerWork bo){
		this.getHibernateTemplate().save(bo);
		return bo.getId();
	}
	public String update(TInnerWork bo){
		this.getHibernateTemplate().update(bo);
		return bo.getId();
	}
	
	public TInnerWork load(String id){
//		TBidPlan t = this.getHibernateTemplate().load(TBidPlan.class, id);
//		if(!Hibernate.isInitialized(t)) 
//			Hibernate.initialize(t); 
		
		TInnerWork t = this.getHibernateTemplate().get(TInnerWork.class, id);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public PageResultSet<Map<String,Object>> list(InnerWorkListVo vo){
		Map<String,Object> map = InnerWorkUtil.generateFilterMap(vo);
		List<Object> src = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		String order = "";
		sql.append("select * from v_inner_work_list t where 1=1 ");
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
				order = " order by " + entry.getValue();
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
		PageInfo pageinfo = new PageInfo(totalRows, InnerWorkUtil.getPageSize(vo.pageSize), InnerWorkUtil.getPage(vo.page));	
		query.setFirstResult(pageinfo.getBeginIndex());
		query.setMaxResults(pageinfo.getPageSize());
		List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		result.setList(list);
		result.setPageInfo(pageinfo);
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public PageResultSet<Map<String,Object>> listAll(InnerWorkListVo vo){
		Map<String,Object> map = InnerWorkUtil.generateFilterMap(vo);
		List<Object> src = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		String order = "";
		sql.append("select * from v_inner_work_list t where 1=1 ");
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
				order = " order by " + entry.getValue();
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
	
	
	
	/**
	 * @author taoweiwei
	 * 作用：根据status查询出内部工作处于非“已完成”状态的数据记录
	 * @return
	 */
    public List<TInnerWork> findByStatus(){
    	String sql="select * from t_inner_work  where status !='已完成' and pf_time is not null and pf_time=to_char(sysdate+7,'yyyy-MM-dd')";
    	Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
    	Query query=session.createSQLQuery(sql).addEntity(TInnerWork.class);
    	List<TInnerWork> tInnerWorks=query.list();
		return tInnerWorks;
    }
	
	
	
	
	
	
}
