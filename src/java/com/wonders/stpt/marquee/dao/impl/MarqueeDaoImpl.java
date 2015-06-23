/**
 * 
 */
package com.wonders.stpt.marquee.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.marquee.dao.MarqueeDao;
import com.wonders.stpt.marquee.model.bo.MarqueeConfigBo;
import com.wonders.stpt.marquee.model.bo.MarqueeMsgBo;

/** 
 * @ClassName: MarqueeDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年2月26日 上午11:30:50 
 *  
 */

@Repository("marqueeDao")
public class MarqueeDaoImpl implements MarqueeDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public MarqueeConfigBo getConfig(){
		MarqueeConfigBo bo = this.getHibernateTemplate().get(MarqueeConfigBo.class, "1");
		return bo;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MarqueeMsgBo> getList(int size,String loginNames){
		String date =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
		List<MarqueeMsgBo> list = new ArrayList<MarqueeMsgBo>();
		String hql = "from MarqueeMsgBo t where t.removed = '0' and t.validStartTime <= ?"
				+ " and t.validEndTime >= ? and (t.loginName is null or t.loginName in ("+loginNames+"))  order by t.priority ,t.app,t.publishTime desc";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().
				createQuery(hql);
		//query.setMaxResults(size);
		query.setParameter(0, date);query.setParameter(1, date);
		list = query.list();
		return list;
	}
}
