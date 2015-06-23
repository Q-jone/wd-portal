package com.wonders.stpt.doneConfig.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.dao.TProcessConfigDao;
import com.wonders.stpt.doneConfig.model.TProcessConfig;
import com.wonders.stpt.doneConfig.service.ITProcessConfigService;
@Repository("processConfigService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TProcessConfigServiceImpl implements ITProcessConfigService {
	@Autowired
	private TProcessConfigDao processConfigDao;
	@Override
	public TProcessConfig save(TProcessConfig tProcessConfig) throws Exception {
		// TODO Auto-generated method stub
		return processConfigDao.save(tProcessConfig);
	}

	@Override
	public int delete(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		return processConfigDao.delete(ids);
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return processConfigDao.delete(id);
	}

	@Override
	public TProcessConfig findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return processConfigDao.findById(id);
	}

	@Override
	public PageResultSet<TProcessConfig> find(TProcessConfig tProcessConfig,
			Integer pageindex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return processConfigDao.find(tProcessConfig, pageindex, pageSize);
	}

	@Override
	public List<TProcessConfig> findByIds(String[] ids, int type)
			throws Exception {
		// TODO Auto-generated method stub
		return processConfigDao.findByIds(ids, type);
	}

	@Override
	public List<TProcessConfig> findByHQL(String hql, Object[] obj)
			throws Exception {
		// TODO Auto-generated method stub
		return processConfigDao.findByHQL(hql, obj);
	}

	@Override
	public int deleteByType(String hql, Object[] obj) throws Exception {
		// TODO Auto-generated method stub
		return processConfigDao.deleteByType(hql, obj);
	}

}
