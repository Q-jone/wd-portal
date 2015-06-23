package com.wonders.stpt.exam.service;

import java.util.List;

import com.wonders.stpt.exam.entity.ExamUserOption;

public interface ExamUserOptionService {
	
	public ExamUserOption find(String id);
	
	public List<ExamUserOption> findByMainId(String mainId);
	
	public ExamUserOption save(ExamUserOption bo);

	public List<ExamUserOption> findByMainIdAndLoginName(String mainId,
			String loginName);

}
