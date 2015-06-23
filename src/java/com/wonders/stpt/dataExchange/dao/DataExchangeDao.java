/** 
* @Title: DataExchangeDao.java 
* @Package com.wonders.stpt.dataExchange.dao 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com 
* @date 2013-8-7 下午03:15:59 
* @version V1.0 
*/
package com.wonders.stpt.dataExchange.dao;

import java.util.HashMap;
import java.util.List;

import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeStore;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeUser;
import com.wonders.stpt.processDone.model.vo.Page;

/** 
 * @ClassName: DataExchangeDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author A18ccms a18ccms_gmail_com 
 * @date 2013-8-7 下午03:15:59 
 *  
 */
public interface DataExchangeDao {
	public DwDataExchangeStore loadById(String id);	
	public Page findListByPage(HashMap<String, String> filter,String sortWay);
	public void update(DwDataExchangeStore transientInstance);	
	public List<DwDataExchangeUser> findSetUsers();
	public boolean ifReceiver(String stepName,String pname,String loginName,String deptId,String userId);
	public void save(Object obj);
	public HashMap<String,String> getEamProjectReportInfo(String loginName);
	public List<HashMap<String,String>> getDeptLeaderName(String oldDeptId);		
}
