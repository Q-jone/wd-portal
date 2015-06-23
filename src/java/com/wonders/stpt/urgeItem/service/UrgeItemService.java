/**
 * 
 */
package com.wonders.stpt.urgeItem.service;

import com.wonders.stpt.urgeItem.model.bo.UrgeItem;

/** 
 * @ClassName: UrgeItemService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-4-22 下午04:02:03 
 *  
 */
public interface UrgeItemService {
	public void save(Object obj);
	
	public void update(Object obj);
	
	public int getCount(String process, String incident);
	
	public UrgeItem getBo(String process, String incident);
}
