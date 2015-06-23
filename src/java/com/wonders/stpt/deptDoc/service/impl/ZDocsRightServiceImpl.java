package com.wonders.stpt.deptDoc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.deptDoc.constants.DeptDocConstants;
import com.wonders.stpt.deptDoc.dao.DocsTDao;
import com.wonders.stpt.deptDoc.model.bo.ZDocsCatalog;
import com.wonders.stpt.deptDoc.model.bo.ZDocsRight;
import com.wonders.stpt.deptDoc.model.vo.ZDocsFileListVo;
import com.wonders.stpt.deptDoc.service.ZDocsCatalogService;
import com.wonders.stpt.deptDoc.service.ZDocsRightService;
import com.wonders.stpt.deptDoc.util.DeptDocProcessUtil;
import com.wonders.stpt.deptDoc.util.DeptDocUtil;

@Repository("zdocsRightService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ZDocsRightServiceImpl implements ZDocsRightService{
	
	private DeptDocUtil deptDocUtil;
	private ZDocsCatalogService zdocsCatalogService;
	
	@Override
	public ZDocsRight findById(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,ZDocsRight.class);
	}
	
	public void initRights(ZDocsFileListVo vo, String loginName, String deptId){
		String catalogId = vo.getCatalogId();
		ZDocsCatalog folder = zdocsCatalogService.findById(catalogId);
		vo.setFolder(folder);
		
		boolean isLeader = DeptDocProcessUtil.existInGroup(DeptDocConstants.CODE_INNER_DEPT_DOC, loginName);
		
		vo.setLeader(isLeader);
		
		if(isLeader || "0".equals(folder.getReadRight()) || "1".equals(vo.getCatalogLevel()) || folder.getCreateUser().endsWith(loginName)){
			vo.setRead(true);
		}else{
			vo.setRead(deptDocUtil.hasReadRight(loginName, folder.getSid()));
		}
		
		if(deptId.equals(folder.getDeptId()) && isLeader){
			vo.setWrite(true);
		}else{
			vo.setWrite(false);
		}
		
	}
	
	public int grantToEmps(String rightId, String empIds, String empNames, String rightType, String uUserId, boolean override){
		int ret = 1;
		
		if(override){
			this.removeExists(rightId);
		}
		
		if(empIds == null || "".equals(empIds)){
		}else{			
			String[] empIdArr = empIds.split(",");
			String[] empNameArr = empNames.split(",");
			for(int i = 0; i<empIdArr.length; i++){
				ZDocsRight r = new ZDocsRight();
				r.setEmpid(empIdArr[i]);
				r.setEmpname(empNameArr[i]);
				r.setRightid(rightId);
				r.setType(rightType);
				r.setUptdate(new Date());
				r.setUptuser("ST/"+uUserId);
				this.dao.save(r);
			}
		}
		return ret;
	}
	
	public int authoity(String fileIds,String empIds,String empNames,String loginName,String userName){
		int ret = 0;
		if(empIds == null || "".equals(empIds) || fileIds == null || "".equals(fileIds)){
			return ret;
		}
		String[] empIdArr = empIds.split(",");
		String[] empNameArr = empNames.split(",");
		String[] fileIdArr = fileIds.split(",");
		ret = 1;
		try{
			for(int i =0;i<fileIdArr.length;i++){
				for(int j = 0; j<empIdArr.length; j++){
					ZDocsRight r = new ZDocsRight();
					r.setEmpid(empIdArr[j]);
					r.setEmpname(empNameArr[j]);
					r.setRightid(fileIdArr[i]);
					r.setType(DeptDocConstants.FILE_RIGHT_TYPE);
					r.setUptdate(new Date());
					r.setUptuser("ST/"+loginName);
					this.dao.save(r);
				}
			}
		}catch(Exception e){
			ret = 0;
		}
		return ret;
	}
	
	
	public int cancel(String[] fileIds,String empIds[]){
		Map<String,Object[]> param = new HashMap<String,Object[]>();
		param.put("rightId", fileIds);
		param.put("empId", empIds);
		return this.dao.updateSQLQuery("delete from z_docs_right r where r.rightid in (:rightId) and r.empid in (:empId) and r.type='1'", param);
	}
	
	public int removeExists(String rightId){
		return this.dao.excuteHQLUpdate("delete from ZDocsRight where rightid = ?", new Object[]{rightId});
	}
	
	public List<ZDocsRight> findByRightId(String rightId){
		return this.dao.findByHql("from ZDocsRight where rightid = ?", new Object[]{rightId});
	}
	
	private DocsTDao<ZDocsRight> dao;
	public DocsTDao<ZDocsRight> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("docsTDao")DocsTDao<ZDocsRight> dao) {
		this.dao = dao;
	}
	
	public ZDocsCatalogService getZdocsCatalogService() {
		return zdocsCatalogService;
	}
	@Autowired(required=false)
	public void setZdocsCatalogService(@Qualifier("zdocsCatalogService")ZDocsCatalogService zdocsCatalogService) {
		this.zdocsCatalogService = zdocsCatalogService;
	}
	
	public DeptDocUtil getDeptDocUtil() {
		return deptDocUtil;
	}
	@Autowired(required=false)
	public void setDeptDocUtil(@Qualifier("deptDocUtil")DeptDocUtil deptDocUtil) {
		this.deptDocUtil = deptDocUtil;
	}
}
