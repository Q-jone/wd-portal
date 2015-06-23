package com.wonders.stpt.exam.service;

import java.util.List;

import com.wonders.stpt.exam.entity.ExamGroup;

public interface ExamGroupService {
	
	public ExamGroup find(String id);
	
	public List<ExamGroup> findByMainId(String mainId);
	
	public ExamGroup save(ExamGroup bo);
	
	public int deleteById(String id);

}
