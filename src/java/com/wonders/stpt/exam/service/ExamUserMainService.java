package com.wonders.stpt.exam.service;


import com.wonders.stpt.exam.entity.ExamUserMain;

public interface ExamUserMainService {
	
	public ExamUserMain find(String id);
	
	public ExamUserMain save(ExamUserMain bo);

	public ExamUserMain findByMainIdAndLoginName(String mainId, String loginName);

}
