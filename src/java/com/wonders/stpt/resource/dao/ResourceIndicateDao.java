/**   
* @Title: ResourceIndicateDao.java 
* @Package com.wonders.stpt.resource.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年4月29日 下午7:59:57 
* @version V1.0   
*/
package com.wonders.stpt.resource.dao;

import java.util.List;

import com.wonders.stpt.resource.model.bo.ResourceIndicate;

/** 
 * @ClassName: ResourceIndicateDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年4月29日 下午7:59:57 
 *  
 */
public interface ResourceIndicateDao {
	public ResourceIndicate findLatestResouece();
	public List<ResourceIndicate> findResoueceByDate(String date);
	public List<ResourceIndicate> findResoueceByDateSpan(String date1,String date2);
}
