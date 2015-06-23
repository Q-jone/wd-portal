/**   
* @Title: ResourceIndicateDaoImpl.java 
* @Package com.wonders.stpt.resource.dao.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年4月29日 下午8:00:19 
* @version V1.0   
*/
package com.wonders.stpt.resource.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.resource.dao.ResourceIndicateDao;
import com.wonders.stpt.resource.model.bo.ResourceIndicate;

/** 
 * @ClassName: ResourceIndicateDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年4月29日 下午8:00:19 
 *  
 */
@Repository("resourceIndicateDao")
public class ResourceIndicateDaoImpl implements ResourceIndicateDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public ResourceIndicate findLatestResouece(){
		String hql = "from ResourceIndicate t where t.removed=0 order by t.indicatorDate desc";
		Query query = this.getHibernateTemplate().getSessionFactory().
				getCurrentSession().createQuery(hql);
		query.setMaxResults(1);
		List<ResourceIndicate> list = query.list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
		
	}
	
	public List<ResourceIndicate> findResoueceByDate(String date){
		String hql = "from ResourceIndicate t where t.removed=0 and t.indicatorDate = ?";
		List<ResourceIndicate> list = this.getHibernateTemplate().find(hql, new Object[]{date});
		return list;
		
	}
	
	public List<ResourceIndicate> findResoueceByDateSpan(String date1,String date2){
		String hql = "from ResourceIndicate t where t.removed=0 and t.indicatorDate >="
				+ " and t.indicatorDate <= ?";
		List<ResourceIndicate> list = this.getHibernateTemplate().find(hql, new Object[]{date1,date2});
		return list;
		
	}
	
}
