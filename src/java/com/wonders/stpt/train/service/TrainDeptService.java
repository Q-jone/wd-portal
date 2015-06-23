package com.wonders.stpt.train.service;

import java.util.Collection;
import java.util.List;

import com.wonders.stpt.train.entity.TrainDept;

public interface TrainDeptService {
	
	public TrainDept find(String id);
	
	public List<TrainDept> findByMainId(String mainId);

	public void saveOrUpdateAll(Collection cols);
	
	public void deleteById(String id);
}
