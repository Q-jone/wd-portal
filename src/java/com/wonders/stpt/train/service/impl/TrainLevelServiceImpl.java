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
import com.wonders.stpt.train.entity.TrainLevel;
import com.wonders.stpt.train.entity.TrainMain;
import com.wonders.stpt.train.service.TrainLevelService;

@Repository("trainLevelService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class TrainLevelServiceImpl implements TrainLevelService{
	
	private TrainTDao<TrainLevel> dao;
	public TrainTDao<TrainLevel> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("trainTDao")TrainTDao<TrainLevel> dao) {
		this.dao = dao;
	}

	@Override
	public TrainLevel find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,TrainLevel.class);
	}
	@Override
	public List<TrainLevel> findByMainId(String mainId) {

		String hql = "from TrainLevel t where t.mainId ='"+mainId+"' order by t.levels ";
		return dao.findByHql(hql);
	}
	
	@Override
	public void saveOrUpdateAll(Collection cols){
		dao.saveOrUpdateAll(cols);
	}
	@Override
	public void deleteById(String id){
		dao.deleteById(id,TrainLevel.class);
	}	
}
