
package com.wonders.stpt.mscp.dao;

import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpOrganTrade;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface MscpOrganTradeDao extends AbstractHibernateDao<MscpOrganTrade> {
	
	/**
	 * 查询组织机构
	 * @return
	 */
	public List<MscpOrganTrade> findMscpOrganTrade(String date);
}
