package com.wonders.stpt.exam.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.exam.dao.ExamTDao;
import com.wonders.stpt.exam.entity.ExamOption;
import com.wonders.stpt.exam.service.ExamOptionService;

@Repository("examOptionService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ExamOptionServiceImpl implements ExamOptionService{
	
	private ExamTDao<ExamOption> dao;
	public ExamTDao<ExamOption> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("examTDao")ExamTDao<ExamOption> dao) {
		this.dao = dao;
	}

	@Override
	public ExamOption find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,ExamOption.class);
	}
	@Override
	public List<ExamOption> findByMainId(String mainId) {

		String hql = "from ExamOption t where t.removed=0 and t.mainId ='"+mainId+"' order by t.opCode asc,t.id desc ";
		return dao.findByHql(hql);
	}
	@Override
	public List<ExamOption> findByQuestionId(String questionId) {

		String hql = "from ExamOption t where t.removed=0 and t.questId ='"+questionId+"' order by t.opCode asc,t.id desc ";
		return dao.findByHql(hql);
	}
	
	@Override
	public int deleteById(String id) {
		return dao.excuteHQLUpdate("update ExamOption set removed = 1 where id = ?", new Object[]{id});
	}
	
	@Override
	public void saveOrUpdateAll(Collection cols){
		dao.saveOrUpdateAll(cols);
	}
	
}
