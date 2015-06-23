package com.wonders.stpt.exam.service;

import java.util.Collection;
import java.util.List;

import com.wonders.stpt.exam.entity.ExamOption;

public interface ExamOptionService {
	
	public ExamOption find(String id);
	
	public List<ExamOption> findByMainId(String mainId);

	public List<ExamOption> findByQuestionId(String questionId);
	
	public int deleteById(String id);
	
	public void saveOrUpdateAll(Collection cols);
}
