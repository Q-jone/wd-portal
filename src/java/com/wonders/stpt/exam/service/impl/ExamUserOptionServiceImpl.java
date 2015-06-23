package com.wonders.stpt.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.exam.dao.ExamTDao;
import com.wonders.stpt.exam.entity.ExamUserOption;
import com.wonders.stpt.exam.service.ExamUserOptionService;

@Repository("examUserOptionService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ExamUserOptionServiceImpl implements ExamUserOptionService{
	
	private ExamTDao<ExamUserOption> dao;
	public ExamTDao<ExamUserOption> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("examTDao")ExamTDao<ExamUserOption> dao) {
		this.dao = dao;
	}

	@Override
	public ExamUserOption find(String id) {
		return dao.find(id,ExamUserOption.class);
	}
	@Override
	public List<ExamUserOption> findByMainId(String mainId) {
		String hql = "from ExamUserOption t where t.removed=0 and t.mainId ='"+mainId+"' ";
		return dao.findByHql(hql);
	}
	@Override
	public ExamUserOption save(ExamUserOption bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			bo.setId(null);
			dao.save(bo);
		}
		return bo;
	}
	@Override
	public List<ExamUserOption> findByMainIdAndLoginName(String mainId,
			String loginName) {
		String hql = "from ExamUserOption t where t.removed=0 and t.mainId ='"+mainId+"' and t.loginName='"+loginName+"' ";
		return dao.findByHql(hql);
	}

}
