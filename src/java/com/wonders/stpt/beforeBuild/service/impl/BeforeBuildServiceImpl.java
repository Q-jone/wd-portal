package com.wonders.stpt.beforeBuild.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.beforeBuild.dao.BeforeBuildDao;
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;

@Repository("beforeBuildService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class BeforeBuildServiceImpl implements BeforeBuildService{
	@Autowired
	private BeforeBuildDao beforeBuildDao;
	
	public void save(Object obj){
		beforeBuildDao.save(obj);
	}
	
	public void saveOrUpdateAll(Collection cols){
		beforeBuildDao.saveOrUpdateAll(cols);
	}
	
	public Object load(String id,Class entityClass){
		return beforeBuildDao.load(id, entityClass);
	}
	
	public List<Map<String,Object>> findByPage(int first,int size,String sql,List<Object> src){
		return beforeBuildDao.findByPage(first, size, sql,src);
	}
	
	public int findPageSize(String sql,List<Object> src){
		return beforeBuildDao.findPageSize(sql,src);
	}
	
	public List<Object[]> findBySql(String sql,List<Object> src){
		return beforeBuildDao.findBySql(sql, src);
	}
	
	public void updateBySql(String sql,List<Object> src){
		beforeBuildDao.updateBySql(sql, src);
	}
}
