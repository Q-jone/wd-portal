
package com.wonders.stpt.mscp.dao.impl;

import java.util.List;

import com.wonders.stpt.mscp.dao.MscpProfileDao;
import com.wonders.stpt.mscp.entity.bo.MscpProfile;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;


public class MscpProfileDaoImpl extends AbstractHibernateDaoImpl<MscpProfile>
		implements MscpProfileDao {

	@SuppressWarnings("unchecked")
	@Override
	public MscpProfile findMscpProfileByDate(String date) {
//		String hql = "from MscpProfile t where t.statDate ='"+date+"'";
		String hql = "from MscpProfile t where t.removed='0' order by to_date(t.statDate,'yyyy-mm-dd') DESC";
		List<MscpProfile> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()==1){
			return list.get(0);
		}
		return null;
	}
	
	
}
