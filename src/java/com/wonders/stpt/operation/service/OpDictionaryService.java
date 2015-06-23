package com.wonders.stpt.operation.service;

import java.util.List;

import com.wonders.stpt.operation.entity.bo.OpDictionary;
import com.wonders.stpt.page.model.PageResultSet;

public interface OpDictionaryService {
	
	public OpDictionary find(String id);
	
	public List<OpDictionary> findByParentId(String pid);

	public List<OpDictionary> findByType(String typecode);
	
	public List<OpDictionary> findByCode(String code);
	
	public OpDictionary save(OpDictionary bo);
	
	public int deleteById(String id);
	
	public List<OpDictionary> findByIds(String id);
	
	public Object[] getReceiver(String deptId);
	
	public Object[] getLeader(String deptId);
	
	public List<OpDictionary> findByDescription(String des);
	
	public List<OpDictionary> findByParentAndDesc(String pid,String desc);
	
}
