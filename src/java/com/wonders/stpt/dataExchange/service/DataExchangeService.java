/** 
* @Title: DataExchangeService.java 
* @Package com.wonders.stpt.dataExchange.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com 
* @date 2013-8-7 下午03:17:35 
* @version V1.0 
*/
package com.wonders.stpt.dataExchange.service;

import java.util.HashMap;
import java.util.List;

import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeStore;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeUser;
import com.wonders.stpt.processDone.model.vo.Page;

/** 
 * @ClassName: DataExchangeService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lushuaifeng 
 * @date 2013-8-7 下午03:17:35 
 *  
 */
public interface DataExchangeService {
	public DwDataExchangeStore loadById(String id);	
	public Page findByPage(HashMap<String, String> filter,String sortWay);
	public String updateDwDataExchangeStore(DwDataExchangeStore dwDataExchangeStore);	
	public List<DwDataExchangeUser> findSetUsers();
	public boolean ifReceiver(String stepName,String pname,String loginName,String deptId,String userId);	
	public String uploadNewFiles(String appName,String[] filePaths,String[] fileNames,String[] fileExtNames,String[] fileSizes,String[] versions,String[] memos,String uploader,String uploaderLoginName);
	public void save(Object obj);
	public HashMap<String,String> getEamProjectReportInfo(String loginName);	
	public List<HashMap<String,String>> getDeptLeaderName(String oldDeptId);		
}
