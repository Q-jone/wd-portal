package com.wonders.stpt.doneConfig.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.doneConfig.dao.TDeptOrUserDao;
import com.wonders.stpt.doneConfig.service.ITDeptOrUserService;
@Repository("deptOrUserService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

public class TDeptOrUserServiceImplementation implements ITDeptOrUserService {

	@Autowired
	private TDeptOrUserDao deptOrUserDao;
	
	@Override
	public List getDept() {
		// TODO Auto-generated method stub
		return deptOrUserDao.getDept();
	}

}
