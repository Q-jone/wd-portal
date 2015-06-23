package com.wonders.stpt.train.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.train.dao.TrainTDao;
import com.wonders.stpt.train.entity.TrainDept;
import com.wonders.stpt.train.entity.TrainMain;
import com.wonders.stpt.train.service.TrainDeptService;

@Repository("trainDeptService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class TrainDeptServiceImpl implements TrainDeptService{
	
	private TrainTDao<TrainDept> dao;
	public TrainTDao<TrainDept> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("trainTDao")TrainTDao<TrainDept> dao) {
		this.dao = dao;
	}

	@Override
	public TrainDept find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,TrainDept.class);
	}
	@Override
	public List<TrainDept> findByMainId(String mainId) {

		String hql = "from TrainDept t where t.mainId ='"+mainId+"' order by t.deptId ";
		return dao.findByHql(hql);
	}
	
	@Override
	public void saveOrUpdateAll(Collection cols){
		dao.saveOrUpdateAll(cols);
	}
	@Override
	public void deleteById(String id){
		dao.deleteById(id,TrainDept.class);
	}	
}
