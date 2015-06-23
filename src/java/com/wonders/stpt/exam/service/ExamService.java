package com.wonders.stpt.exam.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.exam.entity.ExamMain;
import com.wonders.stpt.page.model.PageResultSet;

public interface ExamService {
	
	public ExamMain find(String id);

	public PageResultSet findPageResult(int pageNo, int pageSize);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public ExamMain save(ExamMain bo);
	
	public int deleteById(String id);
	
	public List getReport(String mainId);
}
