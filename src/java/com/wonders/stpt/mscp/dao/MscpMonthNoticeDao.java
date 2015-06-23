
package com.wonders.stpt.mscp.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.mscp.entity.bo.MscpMonthNotice;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface MscpMonthNoticeDao extends AbstractHibernateDao<MscpMonthNotice> {

	
	public List<MscpMonthNotice> findMscpMonthNotice();
	
	public List<Object[]> findMscpMonthNoticeByDate(String date);
	
	public void findMscpMonthNoticeCompare(String now,String last,
			Map<String,String> map1,Map<String,String> map2,
			Map<String,String> map3,
			Map<String,String> map4,
			Map<String,String> map5);
}
