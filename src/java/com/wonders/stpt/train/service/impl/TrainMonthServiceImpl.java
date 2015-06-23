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
import com.wonders.stpt.train.entity.TrainMonth;
import com.wonders.stpt.train.service.TrainMonthService;

@Repository("trainMonthService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class TrainMonthServiceImpl implements TrainMonthService{
	
	private TrainTDao<TrainMonth> dao;
	public TrainTDao<TrainMonth> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("trainTDao")TrainTDao<TrainMonth> dao) {
		this.dao = dao;
	}

	@Override
	public TrainMonth find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,TrainMonth.class);
	}
	@Override
	public List<TrainMonth> findByMainId(String mainId) {

		String hql = "from TrainMonth t where t.mainId ='"+mainId+"' order by t.month ";
		return dao.findByHql(hql);
	}
	
	@Override
	public void saveOrUpdateAll(Collection cols){
		dao.saveOrUpdateAll(cols);
	}
	@Override
	public void deleteById(String id){
		dao.deleteById(id,TrainMonth.class);
	}		
}
