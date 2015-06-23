
package com.wonders.stpt.mscp.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.mscp.entity.bo.MscpMonthPortalVisit;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface MscpMonthPortalVisitDao extends
		AbstractHibernateDao<MscpMonthPortalVisit> {
	
	/**
	 * 查询平台月度访问量
	 * @return
	 */
	public List<MscpMonthPortalVisit> findMscpMonthPortalVisit();
	
	public String findMscpMonthPortalVisitByDate(String date);
	
	public void findMscpPortalVisitCompare(String now,String last,Map<String,String> map);
}
