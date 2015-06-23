package com.wonders.stpt.doneConfig.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.dao.TDoneConfigInfoDao;
import com.wonders.stpt.doneConfig.model.TDoneConfigClassic;
import com.wonders.stpt.doneConfig.model.TDoneConfigInfo;
import com.wonders.stpt.doneConfig.service.TDoneConfigInfoService;
@Repository("doneConfigInfoService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TDoneConfigInfoServiceImpl implements TDoneConfigInfoService {
	@Autowired
	private TDoneConfigInfoDao doneConfigInfoDao;
	@Override
	public TDoneConfigInfo save(TDoneConfigInfo tDoneConfigInfo)
			throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.save(tDoneConfigInfo);
	}

	@Override
	public int delete(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.delete(ids);
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.delete(id);
	}

	@Override
	public TDoneConfigInfo findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.findById(id);
	}

	@Override
	public PageResultSet<TDoneConfigInfo> find(TDoneConfigInfo tDoneConfigInfo,
			Integer pageindex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.find(tDoneConfigInfo, pageindex, pageSize);
	}

	@Override
	public List<TDoneConfigInfo> findByIds(String[] ids, int type)
			throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.findByIds(ids, type);
	}

	@Override
	public List<Object[]> findByHQL(String hql,Map param)
			throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.findByHQL(hql, param);
	}

	@Override
	public List<TDoneConfigInfo> findByLoginName(String loginName) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.findByLoginName(loginName);
	}

	@Override
	public List<TDoneConfigInfo> findByHQL(String hql, Object[] obj)
			throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.findByHQL(hql, obj);
	}

	@Override
	public List<TDoneConfigClassic> getType(String hql, Object[] obj)
			throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.getType(hql, obj);
	}

	@Override
	public String getMaxOrders(String hql, Object[] obj) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.getMaxOrders(hql, obj);
	}

	@Override
	public int delete(String hql, Object[] obj) throws Exception {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.delete(hql, obj);
	}

	@Override
	public int deleteByHql(String hql, Map param) {
		// TODO Auto-generated method stub
		return doneConfigInfoDao.deleteByHql(hql, param);
	}

}
