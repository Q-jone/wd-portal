

package com.wonders.stpt.mscp.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.mscp.entity.bo.MscpMonthVisit;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface MscpMonthVisitDao extends AbstractHibernateDao<MscpMonthVisit> {

	/**
	 * 查询平台月度访问量
	 */
	public List<MscpMonthVisit> findMscpMonthVisit(); 
	
	public String findCountByDate(String date);
	
	public void findMscpMonthVisitCompare(String now,String last,Map<String,String> map);
}
