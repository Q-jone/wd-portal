/**
 * 
 */
package com.wonders.stpt.metroExpress.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.wonders.stpt.metroExpress.dao.MetroExpressDao;
import com.wonders.stpt.metroExpress.entity.bo.MetroExpress;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/** 
 * @ClassName: MetroExpressDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-2-29 下午07:24:45 
 *  
 */
public class MetroExpressDaoImpl extends AbstractHibernateDaoImpl<MetroExpress> implements MetroExpressDao {

	//like 关键字
	public Page findMetroExpressByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from MetroExpress t where t.removed=0";
		String countHql = "select count(*) from MetroExpress t where t.removed=0";
		String filterPart = "";
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
					filterPart += " and ";
				String paramName = i.next();
					if("accidentLine".equals(paramName)){
						filterPart += "t." + paramName + " = :" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}else{
						filterPart += "t." + paramName + " like :" + paramName;
						args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
					}
			}
		}
		filterPart += " order by t.accidentDate desc ,t.accidentTime desc";
		
		
		//System.out.println(hql + filterPart+"++++++++++++++");
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	//按热度取对应条数记录
	public List<MetroExpress> findLatestMetroExpressEvents(String accidentLine, String accidentEmergency, int size){
		String hql = "from MetroExpress m where m.removed = '0' and m.accidentActive = '0'";
		if(!"".equals(accidentLine)&&!"0".equals(accidentLine)){
			hql += " and m.accidentLine = '"+accidentLine+"'";
		}
		if(!"".equals(accidentEmergency)){
			hql += " and m.accidentEmergency = '"+accidentEmergency+"'";
		}
		hql += " order by m.accidentDate desc ,m.accidentTime desc ";
		//System.out.println(hql+"--------------------------------");
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		
		//sql = "select c.login_name ,c.name from cs_user c where 'ST/' ||  c.login_name = (select o.taskuser from tasks o where o.processname = '"+ processName + "' and o.incident = "+Long.valueOf(pinstanceId) +" and o.steplabel like '%领导%'and o.recipient <> 'WONDERS-TEST_WF' ) and c.removed = 0";
		
		//Query query = session.createSQLQuery(sql);
		
		query.setMaxResults(size);
		List<MetroExpress> list = query.list();
		return list;
	}

}
