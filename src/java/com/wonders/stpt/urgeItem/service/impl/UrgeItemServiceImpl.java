/**
 * 
 */
package com.wonders.stpt.urgeItem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.urgeItem.dao.UrgeItemDao;
import com.wonders.stpt.urgeItem.model.bo.UrgeItem;
import com.wonders.stpt.urgeItem.service.UrgeItemService;

/** 
 * @ClassName: UrgeItemServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-4-22 下午04:02:21 
 *  
 */
@Repository("urgeItemService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UrgeItemServiceImpl implements UrgeItemService{

	private UrgeItemDao urgeItemDao;
	
	
	
	public UrgeItemDao getUrgeItemDao() {
		return urgeItemDao;
	}

	@Autowired(required=false)
	public void setUrgeItemDao(@Qualifier("urgeItemDao")UrgeItemDao urgeItemDao) {
		this.urgeItemDao = urgeItemDao;
	}

	/** 
	* @Title: save 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param obj    设定文件 
	* @throws 
	*/
	@Override
	public void save(Object obj) {
		// TODO Auto-generated method stub
		this.urgeItemDao.save(obj);
	}

	/** 
	* @Title: update 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param obj    设定文件 
	* @throws 
	*/
	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		this.urgeItemDao.update(obj);
	}
		
	@Override
	public int getCount(String process, String incident){
		return this.urgeItemDao.getCount(process, incident);
	}
	
	@Override
	public UrgeItem getBo(String process, String incident){
		return this.urgeItemDao.getBo(process, incident);
	}
}
