package com.wonders.stpt.train.service;

import java.util.Collection;
import java.util.List;

import com.wonders.stpt.train.entity.TrainDept;
import com.wonders.stpt.train.entity.TrainMonth;

public interface TrainMonthService {
	
	public TrainMonth find(String id);
	
	public List<TrainMonth> findByMainId(String mainId);

	public void saveOrUpdateAll(Collection cols);
	
	public void deleteById(String id);
}
