package com.wonders.stpt.deptDoc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.deptDoc.dao.DeptDocProcessDao;
import com.wonders.stpt.deptDoc.model.bo.ZDocsRight;
import com.wonders.stpt.deptDoc.model.vo.PageQueryVo;
import com.wonders.stpt.deptDoc.service.DeptDocProcessService;
import com.wonders.stpt.page.model.PageResultSet;

@Service("deptDocProcessService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class DeptDocProcessServiceImpl implements DeptDocProcessService{
	@Autowired
	private DeptDocProcessDao dao;
	
	public PageResultSet<Map<String,Object>> list(String sourceSql,PageQueryVo vo){
		return this.dao.list(sourceSql, vo);
	}
	
	
	public boolean authority(List<ZDocsRight> rights){
		return this.dao.authority(rights);
	}
	
	public boolean cancel(String[] fileId,String[] empId){
		return this.dao.cancel(fileId,empId);
	}
}
