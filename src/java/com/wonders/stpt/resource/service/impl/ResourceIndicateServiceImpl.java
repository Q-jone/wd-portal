/**   
* @Title: ResourceIndicateServiceImpl.java 
* @Package com.wonders.stpt.resource.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年4月29日 下午8:52:12 
* @version V1.0   
*/
package com.wonders.stpt.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.resource.dao.ResourceIndicateDao;
import com.wonders.stpt.resource.service.ResourceIndicateService;

/** 
 * @ClassName: ResourceIndicateServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年4月29日 下午8:52:12 
 *  
 */

@Service("resourceIndicateService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ResourceIndicateServiceImpl implements ResourceIndicateService{
	private ResourceIndicateDao dao;

	public ResourceIndicateDao getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("resourceIndicateDao")ResourceIndicateDao dao) {
		this.dao = dao;
	}
	
	
	
}
