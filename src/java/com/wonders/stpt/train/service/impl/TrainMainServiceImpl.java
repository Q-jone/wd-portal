package com.wonders.stpt.train.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.train.dao.TrainTDao;
import com.wonders.stpt.train.entity.TrainMain;
import com.wonders.stpt.train.entity.TrainProfile;
import com.wonders.stpt.train.service.TrainMainService;

@Repository("trainMainService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class TrainMainServiceImpl implements TrainMainService{
	
	private TrainTDao<TrainMain> dao;
	public TrainTDao<TrainMain> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("trainTDao")TrainTDao<TrainMain> dao) {
		this.dao = dao;
	}

	private TrainTDao<TrainProfile> profileDao;
	public TrainTDao<TrainProfile> getProfileDao() {
		return profileDao;
	}
	@Autowired(required=false)
	public void setProfileDao(@Qualifier("trainTDao")TrainTDao<TrainProfile> profileDao) {
		this.profileDao = profileDao;
	}
	
	@Override
	public TrainMain find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,TrainMain.class);
	}
	@Override
	public TrainMain save(TrainMain bo) {
		// TODO Auto-generated method stub
		return dao.save(bo);
	}
	@Override
	public List<TrainMain> findByYear(String year) {
		// TODO Auto-generated method stub
		String hql = "from TrainMain t where t.year ='"+year+"' order by t.type";
		return dao.findByHql(hql);
	}
	@Override
	public List<TrainMain> findAll() {
		// TODO Auto-generated method stub
		String hql = "from TrainMain t order by t.year desc,t.type";
		return dao.findByHql(hql);
	}
	@Override
	public void saveOrUpdateAll(Collection cols){
		dao.saveOrUpdateAll(cols);
	}
	@Override
	public void deleteById(String id){
		dao.deleteById(id,TrainMain.class);
	}		

	@Override
	public void saveAll(Collection cols,String year,String deadline){
		List<TrainProfile> profiles = profileDao.findByHql("from TrainProfile where year = '"+year+"'");
		if(profiles != null && profiles.size() > 0){
			TrainProfile profile = profiles.get(0);
			profile.setDeadline(deadline);
			profileDao.update(profile);
		}else{
			TrainProfile profile = new TrainProfile();
			profile.setYear(year);
			profile.setDeadline(deadline);
			profileDao.save(profile);
		}
		
		dao.saveOrUpdateAll(cols);
	}
	
	@Override
	public TrainProfile findProfileByYear(String year) {
		List<TrainProfile> profiles = profileDao.findByHql("from TrainProfile where year = '"+year+"'");
		if(profiles != null && profiles.size()>0){
			return profiles.get(0);
		}
		return null;
	}
}
