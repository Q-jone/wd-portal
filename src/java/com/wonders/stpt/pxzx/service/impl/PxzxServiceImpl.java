package com.wonders.stpt.pxzx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.pxzx.dao.PxzxDao;
import com.wonders.stpt.pxzx.service.PxzxService;

@Repository("pxzxService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class PxzxServiceImpl implements PxzxService{
	@Autowired
	private PxzxDao pxzxDao;
	
	public List<Object[]> findBySql(String sql,List<Object> src){
		return pxzxDao.findBySql(sql, src);
	}
}
