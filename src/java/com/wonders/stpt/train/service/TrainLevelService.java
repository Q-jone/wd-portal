package com.wonders.stpt.train.service;

import java.util.Collection;
import java.util.List;

import com.wonders.stpt.train.entity.TrainDept;
import com.wonders.stpt.train.entity.TrainLevel;

public interface TrainLevelService {
	
	public TrainLevel find(String id);
	
	public List<TrainLevel> findByMainId(String mainId);

	public void saveOrUpdateAll(Collection cols);
	
	public void deleteById(String id);
}
