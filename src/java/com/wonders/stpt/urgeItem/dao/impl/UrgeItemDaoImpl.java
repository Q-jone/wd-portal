/**
 * 
 */
package com.wonders.stpt.urgeItem.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.urgeItem.dao.UrgeItemDao;
import com.wonders.stpt.urgeItem.model.bo.UrgeItem;

/** 
 * @ClassName: UrgeItemDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-4-22 下午03:59:20 
 *  
 */

@Repository("urgeItemDao")
public class UrgeItemDaoImpl implements UrgeItemDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	/** 
	* @Title: save 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param obj    设定文件 
	* @throws 
	*/
	@Override
	public void save(Object obj) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(obj);
	}
	/** 
	* @Title: update 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param obj    设定文件 
	* @throws 
	*/
	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(obj);
	}
	
	@Override
	public int getCount(String process, String incident){
		String hql = "from UrgeItem t where t.process=? and t.incident=? and t.removed ='0' and t.status = '0'";
		return this.getHibernateTemplate().find(hql, new Object[]{process, incident}).size();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public UrgeItem getBo(String process, String incident){
		String hql = "from UrgeItem t where t.process=? and t.incident=? and t.removed ='0' and t.status = '0'";
		List<UrgeItem> list = this.getHibernateTemplate().find(hql, new Object[]{process, incident});
		if(list !=null && list.size() > 0){
			return list.get(0);
		}

		return null;
		
	}
	
}
