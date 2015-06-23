/**
 * 
 */
package com.wonders.stpt.metroExpress.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.metroExpress.entity.bo.MetroExpress;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/** 
 * @ClassName: MetroExpressDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-2-29 下午07:29:34 
 *  
 */
public interface MetroExpressDao extends AbstractHibernateDao<MetroExpress> {
	public List<MetroExpress> findLatestMetroExpressEvents(String accidentLine, String accidentEmergency, int size);
	
	public Page findMetroExpressByPage(Map<String, Object> filter,int pageNo, int pageSize);
}
