package com.wonders.stpt.doneConfig.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.dao.TDoneConfigUserDao;
import com.wonders.stpt.doneConfig.model.TDoneConfigUser;
import com.wonders.stpt.doneConfig.service.ITDoneConfigUserService;
@Repository("doneConfigUserService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TDoneConfigUserServiceImpl implements ITDoneConfigUserService {
	@Autowired
	private TDoneConfigUserDao doneConfigUserDao;
	@Override
	public TDoneConfigUser save(TDoneConfigUser doneConfigUser)
			throws Exception {
		// TODO Auto-generated method stub
		return doneConfigUserDao.save(doneConfigUser);
	}

	@Override
	public int delete(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigUserDao.delete(ids);
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigUserDao.delete(id);
	}

	@Override
	public TDoneConfigUser findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigUserDao.findById(id);
	}

	@Override
	public PageResultSet<TDoneConfigUser> find(TDoneConfigUser doneConfigUser,
			Integer pageindex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigUserDao.find(doneConfigUser, pageindex, pageSize);
	}

	@Override
	public List<TDoneConfigUser> findList(String hql, Object[] obj)
			throws Exception {
		// TODO Auto-generated method stub
		return doneConfigUserDao.findList(hql, obj);
	}

}
