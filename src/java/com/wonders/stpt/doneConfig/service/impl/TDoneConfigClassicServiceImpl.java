package com.wonders.stpt.doneConfig.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.dao.TDoneConfigClassicDao;
import com.wonders.stpt.doneConfig.model.TDoneConfigClassic;
import com.wonders.stpt.doneConfig.service.ITDoneConfigClassicService;
@Repository("doneConfigClassicService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TDoneConfigClassicServiceImpl implements ITDoneConfigClassicService {
	@Autowired
	private TDoneConfigClassicDao doneConfigClassicDao;
	@Override
	public TDoneConfigClassic save(TDoneConfigClassic doneConfigClassic) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigClassicDao.save(doneConfigClassic);
	}

	@Override
	public int delete(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigClassicDao.delete(ids);
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigClassicDao.delete(id);
	}

	@Override
	public TDoneConfigClassic findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigClassicDao.findById(id);
	}

	@Override
	public PageResultSet<TDoneConfigClassic> find(TDoneConfigClassic doneConfigClassic,
			Integer pageindex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigClassicDao.find(doneConfigClassic, pageindex, pageSize);
	}

	@Override
	public List getRefId(String[] name) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigClassicDao.getRefId(name);
	}

	@Override
	public List<String> getName() throws Exception {
		// TODO Auto-generated method stub
		return doneConfigClassicDao.getName();
	}
	public List<TDoneConfigClassic> findByHql(String hql,Map param)throws Exception{
		return doneConfigClassicDao.findByHql(hql, param);
	}
}
