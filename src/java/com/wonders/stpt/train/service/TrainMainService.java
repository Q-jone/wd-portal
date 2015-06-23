package com.wonders.stpt.train.service;


import java.util.Collection;
import java.util.List;

import com.wonders.stpt.train.entity.TrainMain;
import com.wonders.stpt.train.entity.TrainProfile;

public interface TrainMainService {
	
	public TrainMain find(String id);
	
	public TrainMain save(TrainMain bo);

	public List<TrainMain> findByYear(String year);
	
	public List<TrainMain> findAll();
	
	public void saveOrUpdateAll(Collection cols);
	
	public void deleteById(String id);
	
	public void saveAll(Collection cols,String year,String deadline);
	
	public TrainProfile findProfileByYear(String year);

}
