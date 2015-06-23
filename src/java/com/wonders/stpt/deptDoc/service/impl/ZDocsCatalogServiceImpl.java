package com.wonders.stpt.deptDoc.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.deptDoc.dao.DocsTDao;
import com.wonders.stpt.deptDoc.model.bo.ZDocsCatalog;
import com.wonders.stpt.deptDoc.service.ZDocsCatalogService;
import com.wonders.stpt.deptDoc.service.ZDocsRightService;
import com.wonders.stpt.deptDoc.util.DeptDocUtil;

@Repository("zdocsCatalogService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ZDocsCatalogServiceImpl implements ZDocsCatalogService{
	
	private DeptDocUtil deptDocUtil;
	
	private static final String CATALOG_SHARE = "200";
	private static final String CATALOG_DEPT = "100";
	
	@Override
	public ZDocsCatalog findById(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,ZDocsCatalog.class);
	}
	
	@Override
	public int removeById(String id) {
		// TODO Auto-generated method stub
		return dao.excuteHQLUpdate("update ZDocsCatalog set state = '0' where sid = ?", new Object[]{id});
	}
	
	public List getFolders(String flag, String loginName, String deptId){
		if(CATALOG_DEPT.equals(flag)){
			return deptDocUtil.getDeptFolders(loginName,deptId,"");
		}else if(CATALOG_SHARE.equals(flag)){
			return deptDocUtil.getShareFolders();
		}
		return Collections.EMPTY_LIST;
	}
	
	public List getSelectableFolders(String flag, String loginName, String deptId,String catalogId){
		if(CATALOG_DEPT.equals(flag)){
			return deptDocUtil.getDeptFolders(loginName,deptId,catalogId);
		}else if(CATALOG_SHARE.equals(flag)){
			return deptDocUtil.getOwnShareFolders(deptId,catalogId);
		}
		return Collections.EMPTY_LIST;
	}
	
	public ZDocsCatalog saveDir(String id, String pid, String catalogName, String empIds, String empNames, String cUserId,String cUserName){
		ZDocsCatalog dir = new ZDocsCatalog();
		Date now = new Date();
		if("".equals(id)){
			ZDocsCatalog parentDir = this.findById(pid);
			dir.setDeptId(parentDir.getDeptId());
			dir.setFlag(parentDir.getFlag());
			dir.setParentSid(pid);
			dir.setReadRight(parentDir.getReadRight());
			dir.setState(parentDir.getState());
			dir.setWriteRight(parentDir.getWriteRight());
			dir.setCreateDate(now);
			dir.setCreateUser("ST/"+cUserId);
			dir.setCreateUserName(cUserName);
		}else{
			dir = this.findById(id);
		}
		
		dir.setUpdateDate(now);
		dir.setUpdateUser("ST/"+cUserId);
		dir.setUpdateUserName(cUserName);		
		dir.setCatalogName(catalogName);
		dir = this.dao.save(dir);
		
		zdocsRightService.grantToEmps(dir.getSid(), empIds, empNames, "0", cUserId, true);
		
		return dir;
	}
	
	private DocsTDao<ZDocsCatalog> dao;
	public DocsTDao<ZDocsCatalog> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("docsTDao")DocsTDao<ZDocsCatalog> dao) {
		this.dao = dao;
	}
	
	private ZDocsRightService zdocsRightService;
	public ZDocsRightService getZdocsRightService() {
		return zdocsRightService;
	}
	@Autowired(required=false)
	public void setZdocsRightService(@Qualifier("zdocsRightService")ZDocsRightService zdocsRightService) {
		this.zdocsRightService = zdocsRightService;
	}
	
	public DeptDocUtil getDeptDocUtil() {
		return deptDocUtil;
	}
	@Autowired(required=false)
	public void setDeptDocUtil(@Qualifier("deptDocUtil")DeptDocUtil deptDocUtil) {
		this.deptDocUtil = deptDocUtil;
	}
}
