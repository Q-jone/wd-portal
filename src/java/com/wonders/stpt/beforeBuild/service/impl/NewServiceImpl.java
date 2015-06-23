package com.wonders.stpt.beforeBuild.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.beforeBuild.dao.NewDao;
import com.wonders.stpt.beforeBuild.service.NewService;

@Repository("newService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class NewServiceImpl implements NewService{
	@Autowired
	private NewDao newDao;
	
	public List<Object[]> findBySql(String sql,List<Object> src){
		return newDao.findBySql(sql, src);
	}
}
