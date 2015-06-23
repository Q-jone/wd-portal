/** 
* @Title: DataExchangeServiceImpl.java 
* @Package com.wonders.stpt.dataExchange.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com 
* @date 2013-8-7 下午03:17:06 
* @version V1.0 
*/
package com.wonders.stpt.dataExchange.service.impl;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.wonders.stpt.attach.util.AttachUtil;
import com.wonders.stpt.dataExchange.dao.AttachDao;
import com.wonders.stpt.dataExchange.dao.DataExchangeDao;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeStore;
import com.wonders.stpt.dataExchange.model.bo.DwDataExchangeUser;
import com.wonders.stpt.dataExchange.model.bo.OldAttachFile;
import com.wonders.stpt.dataExchange.service.DataExchangeService;
import com.wonders.stpt.processDone.model.vo.Page;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;


/** 
 * @ClassName: DataExchangeServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lushuaifeng 
 * @date 2013-8-7 下午03:17:06 
 *  
 */
@Repository("dataExchangeService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class DataExchangeServiceImpl implements DataExchangeService{
	@Autowired
	private DataExchangeDao dataExchangeDao;
	
	@Autowired
	private AttachDao attachDao;
	
	public DwDataExchangeStore loadById(String id){
		DwDataExchangeStore msg=dataExchangeDao.loadById(id);
		return msg;
	}	

	public Page findByPage(HashMap<String, String> filter,String sortWay){
		Page page=dataExchangeDao.findListByPage(filter, sortWay);
		return page;
	}	
	
	public String updateDwDataExchangeStore(DwDataExchangeStore dwDataExchangeStore){
		dataExchangeDao.update(dwDataExchangeStore);
		return "ok";
	}		
	
	public List<DwDataExchangeUser> findSetUsers() {
		return dataExchangeDao.findSetUsers();
	}

	public boolean ifReceiver(String stepName,String pname,String loginName,String deptId,String userId){
		return dataExchangeDao.ifReceiver(stepName,pname, loginName, deptId,userId);
	}
	
	
	public String uploadNewFiles(String appName,String[] filePaths,String[] fileNames,String[] fileExtNames,String[] fileSizes,String[] versions,String[] memos,String uploader,String uploaderLoginName){

		AttachUtil ins = AttachUtil.getInstance();
		String fileGroupCode = ins.getFileGroupCode();
		String currentDate = DateUtil.getCurrDate("yyyy-MM-dd hh:mm:ss");		
		for(int i=0;i<filePaths.length;i++){

			String fileName="";
			String fileExtName="";
			String version="";
			String fileSize="";
			String memo="";
			
			if(i<fileNames.length){fileName=fileNames[i];}			
			if(i<fileExtNames.length){fileExtName=fileExtNames[i];}			
			if(i<fileSizes.length){fileSize=fileSizes[i];}			
			if(i<versions.length){version=versions[i];}				
			if(i<memos.length){memo=memos[i];}		
			
			String[] fs=filePaths[i].split("/");
			
			if(fileName==null || fileName.equals("")){
				fileName=fs[fs.length-1].split("\\.")[0];
			}

			if(fileExtName==null || fileExtName.equals("")){
				fileExtName=fs[fs.length-1].split("\\.")[1];;
			}			

			if(fileSize==null || fileSize.equals("")){
				fileSize="0";
			}
					
			if(version==null || version.equals("")){
				version="1";
			}			

			if(memo==null || memo.equals("")){
				memo="附件";
			}	
			
			OldAttachFile attachFile = new OldAttachFile();

			attachFile.setFileName(fileName);
			attachFile.setFileExtName(fileExtName);	
			attachFile.setPath(filePaths[i]);			
			attachFile.setFileSize(Long.valueOf(fileSize));
			attachFile.setUploader(uploader);
			attachFile.setUploadDate(currentDate);			
			attachFile.setGroupId(fileGroupCode);
			attachFile.setRemoved(0);
			attachFile.setOperateTime(System.currentTimeMillis());
			attachFile.setAppName(appName);
			attachFile.setUploaderLoginName(uploaderLoginName);

			attachFile.setMemo(memo);
			attachFile.setVersion(version);			
			attachFile.setStatus(AttachUtil.STATUS_UPLOAD);
			try{
				this.attachDao.getHibernateTemplate().save(attachFile);	//文件信息保存至数据库
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return fileGroupCode;
	}	
	
	public void save(Object obj){
		dataExchangeDao.save(obj);
	}
	
	public HashMap<String,String> getEamProjectReportInfo(String loginName){
		return dataExchangeDao.getEamProjectReportInfo(loginName);
	}
	
	public List<HashMap<String,String>> getDeptLeaderName(String oldDeptId){
		return dataExchangeDao.getDeptLeaderName(oldDeptId);
	}	
}
