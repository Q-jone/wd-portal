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

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.attach.model.bo.AttachFile;
import com.wonders.stpt.attach.util.AttachUtil;
import com.wonders.stpt.dataExchange.dao.AttachDao;
import com.wonders.stpt.dataExchange.dao.DataExchangeDao;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeStore;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeUser;
import com.wonders.stpt.processDone.model.vo.Page;
import com.wonders.stpt.processDone.util.Constants;


/** 
 * @ClassName: AttachDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lushuaifeng 
 * @date 2013-8-7 下午03:15:31 
 *  
 */
@Repository("attachDao")
public class AttachDaoImpl implements AttachDao{
	private HibernateTemplate hibernateTemplate2;	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate2;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate2) {
		this.hibernateTemplate2 = hibernateTemplate2;
	}
	
	@SuppressWarnings("unchecked")
	public List findByHQL(String hql, Object... params) {
		List list = new ArrayList();
		if(params!=null&&params.length>0){
			list = this.getHibernateTemplate().find(hql,params);
		}else{
			list = this.getHibernateTemplate().find(hql);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<AttachFile> findFilesByGroupId(String groupId){
		String hql = "from OldAttachFile attach where " +
				"attach.status=? and attach.groupId=? and attach.removed=0 order by attach.uploadDate desc";
		return this.findByHQL(hql,AttachUtil.STATUS_UPLOAD,groupId);
	}
}
