/** 
* @Title: AttachDao.java 
* @Package com.wonders.stpt.dataExchange.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com 
* @date 2013-8-29 下午06:36:55 
* @version V1.0 
*/
package com.wonders.stpt.dataExchange.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.wonders.stpt.attach.model.bo.AttachFile;

/** 
 * @ClassName: AttachDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author A18ccms a18ccms_gmail_com 
 * @date 2013-8-29 下午06:36:55 
 *  
 */
public interface AttachDao {
	HibernateTemplate getHibernateTemplate();
	
	public List<AttachFile> findFilesByGroupId(String groupId);
}
