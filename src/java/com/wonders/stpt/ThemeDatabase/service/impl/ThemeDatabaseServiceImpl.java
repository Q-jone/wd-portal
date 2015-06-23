package com.wonders.stpt.ThemeDatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.ThemeDatabase.dao.ThemeDatabaseDao;
import com.wonders.stpt.ThemeDatabase.service.ThemeDatabaseService;

@Repository("themeDatabaseService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ThemeDatabaseServiceImpl implements ThemeDatabaseService{
	@Autowired
	private ThemeDatabaseDao themeDatabaseDao;
	
	public Object load(String id,Class entityClass){
		return themeDatabaseDao.load(id, entityClass);
	}
	
	public List<Object[]> findBySql(String sql,List<Object> src){
		return themeDatabaseDao.findBySql(sql, src);
	}
}
