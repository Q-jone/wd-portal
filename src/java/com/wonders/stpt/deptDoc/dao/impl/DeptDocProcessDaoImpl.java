package com.wonders.stpt.deptDoc.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.deptDoc.dao.DeptDocProcessDao;
import com.wonders.stpt.deptDoc.model.bo.ZDocsFile;
import com.wonders.stpt.deptDoc.model.bo.ZDocsRight;
import com.wonders.stpt.deptDoc.model.vo.PageQueryVo;
import com.wonders.stpt.deptDoc.util.DeptDocProcessUtil;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;


@Repository("deptDocProcessDao")
public class DeptDocProcessDaoImpl implements DeptDocProcessDao{
	
	@Resource(name="hibernateTemplate2")
	private HibernateTemplate hibernateTemplate;
	
	//设置权限
	public boolean authority(List<ZDocsRight> rights){
		boolean flag = false;
		try{
			this.hibernateTemplate.saveOrUpdateAll(rights);
			flag = true;
		}catch(Exception e){
			
		}
		return flag;
	}
	
	//设置权限
	public boolean cancel(final String[] fileId,final String[] empId){
		int result = 1;
		try{
			this.hibernateTemplate.execute(new HibernateCallback<Integer>() {  
		
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {  
                Query q = session.createSQLQuery("delete from z_docs_right r where r.rightid in (:rightId) and r.empid in (:empId) and r.type='1'" );
                q.setParameterList("rightId", fileId);
                q.setParameterList("empId", empId);
                return q.executeUpdate();  
            }  
        });
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		if(result > 0){
			return true;
		}
		return false;
	}
	
	
	
	@SuppressWarnings({ "unchecked"})
	public PageResultSet<Map<String,Object>> list(String sourceSql , PageQueryVo vo){
		Map<String,Object> map = DeptDocProcessUtil.generateFilterMap(vo);
		Map<String,Object> src = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer();		
		String order = "";
		String key = "";
		sql.append(sourceSql);
		for(Map.Entry<String, Object> entry : map.entrySet()){
			if(entry.getKey().indexOf("_equal") > 0){
				key = entry.getKey().replace("_equal", "");
				sql.append(" and t."+key+" = :"+key);
				src.put(key,entry.getValue());
			}else if(entry.getKey().indexOf("_in") > 0){
				key = entry.getKey().replace("_in", "");
				sql.append(" and t."+key+" in (:"+key+") ");
				String[] inEntry = entry.getValue().toString().split(",");
				src.put(key,java.util.Arrays.asList(inEntry));
			}else if(entry.getKey().indexOf("_like") > 0){
				key = entry.getKey().replace("_like", "");
				sql.append(" and t."+key+" like :"+key+" escape '/'");
				src.put(key,"%"+entry.getValue().toString().replaceAll("%","/%").replaceAll("_","/_")+"%");
			}else if(entry.getKey().indexOf("_startDate") > 0){
					key = entry.getKey().replace("_startDate", "");
					sql.append(" and t."+key+" >= :"+entry.getKey()+" ");
					src.put(entry.getKey(),entry.getValue());
			}else if(entry.getKey().indexOf("_endDate") > 0){
				key = entry.getKey().replace("_endDate", "");
				sql.append(" and t."+key+" <= :"+entry.getKey()+" ");
				src.put(entry.getKey(),entry.getValue());
			}else if("order".equals(entry.getKey())){
				order = " order by t." + entry.getValue();
			}
		}
		sql.append(order);
		PageResultSet<Map<String,Object>> result = new PageResultSet<Map<String,Object>>();
		Query query = this.hibernateTemplate.getSessionFactory().
		getCurrentSession().createSQLQuery(sql.toString()).
		setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for(Map.Entry<String, Object> entry:src.entrySet()){
			if(entry.getValue() instanceof java.util.List){
				query.setParameterList(entry.getKey(), (Collection<String>) entry.getValue());
			}else{
				query.setParameter(entry.getKey(), entry.getValue());
			}
			
			
		}
		int totalRows = query.list().size();
		PageInfo pageinfo = new PageInfo(totalRows, DeptDocProcessUtil.getPageSize(vo.pageSize), DeptDocProcessUtil.getPage(vo.page));	
		query.setFirstResult(pageinfo.getBeginIndex());
		query.setMaxResults(pageinfo.getPageSize());
		List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		result.setList(list);
		result.setPageInfo(pageinfo);
		return result;
	}
}
