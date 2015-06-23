/**
 * 
 */
package com.wonders.stpt.validFile.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.validFile.dao.ValidFileDao;
import com.wonders.stpt.validFile.model.vo.ValidFileListVo;
import com.wonders.stpt.validFile.util.ValidFileUtil;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;

/** 
 * @ClassName: ValidFileDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月22日 上午11:24:05 
 *  
 */
@Repository("validFileDao")
public class ValidFileDaoImpl implements ValidFileDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public PageResultSet<Map<String,Object>> list(StringBuffer sql,ValidFileListVo vo){
		Map<String,Object> map = ValidFileUtil.generateFilterMap(vo);
		Map<String,Object> src = new HashMap<String,Object>();
		String order = "";
		String key = "";
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
					sql.append(" and t."+key+" >= :"+key+" ");
					src.put(key,entry.getValue());
			}else if(entry.getKey().indexOf("_endDate") > 0){
				key = entry.getKey().replace("_endDate", "");
				sql.append(" and t."+key+" <= :"+key+" ");
				src.put(key,entry.getValue());
			}else if("order".equals(entry.getKey())){
				order = " order by t." + entry.getValue();
			}
		}
		sql.append(order);
		PageResultSet<Map<String,Object>> result = new PageResultSet<Map<String,Object>>();
		Query query = this.getHibernateTemplate().getSessionFactory().
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
		PageInfo pageinfo = new PageInfo(totalRows, ValidFileUtil.getPageSize(vo.pageSize), ValidFileUtil.getPage(vo.page));	
		query.setFirstResult(pageinfo.getBeginIndex());
		query.setMaxResults(pageinfo.getPageSize());
		List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		result.setList(list);
		result.setPageInfo(pageinfo);
		return result;
	}
}
