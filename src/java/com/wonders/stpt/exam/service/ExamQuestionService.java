package com.wonders.stpt.exam.service;

import java.util.List;

import com.wonders.stpt.exam.entity.ExamGroup;
import com.wonders.stpt.exam.entity.ExamQuestion;

public interface ExamQuestionService {
	
	public ExamQuestion find(String id);
	
	public List<ExamQuestion> findByMainId(String mainId);

	public List<ExamQuestion> findByGroupId(String groupId);
	
	public ExamQuestion save(ExamQuestion bo,String[] sid,String[] options);
	
	public int deleteById(String id);
}
