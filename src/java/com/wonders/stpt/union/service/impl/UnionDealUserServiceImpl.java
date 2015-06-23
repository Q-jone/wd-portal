package com.wonders.stpt.union.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.union.dao.UnionTDao;
import com.wonders.stpt.union.entity.bo.UnionDealUser;
import com.wonders.stpt.union.service.UnionDealUserService;

@Repository("unionDealUserService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionDealUserServiceImpl implements UnionDealUserService{
	
	private UnionTDao<UnionDealUser> dao;
	public UnionTDao<UnionDealUser> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionDealUser> dao) {
		this.dao = dao;
	}

	@Override
	public UnionDealUser find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionDealUser.class);
	}
	
	@Override
	public List<UnionDealUser> findByStatusAndStage(int status, int stage) {
		// TODO Auto-generated method stub
		return dao.findByHql("from UnionDealUser where status = ? and stage = ?",new Object[]{status, stage});
	}
	

}
