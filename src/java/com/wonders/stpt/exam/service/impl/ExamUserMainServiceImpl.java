package com.wonders.stpt.exam.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.exam.dao.ExamTDao;
import com.wonders.stpt.exam.entity.ExamUserMain;
import com.wonders.stpt.exam.service.ExamUserMainService;

@Repository("examUserMainService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ExamUserMainServiceImpl implements ExamUserMainService{
	
	private ExamTDao<ExamUserMain> dao;
	public ExamTDao<ExamUserMain> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("examTDao")ExamTDao<ExamUserMain> dao) {
		this.dao = dao;
	}

	@Override
	public ExamUserMain find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,ExamUserMain.class);
	}
	@Override
	public ExamUserMain save(ExamUserMain bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	}
	@Override
	public ExamUserMain findByMainIdAndLoginName(String mainId, String loginName) {
		
		
		
		String hql = "from ExamUserMain t where t.removed=0 and t.mainId ='"+mainId+"' and t.loginName='"+loginName+"' ";
		List<ExamUserMain> list = dao.findByHql(hql);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
