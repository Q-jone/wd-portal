package com.wonders.stpt.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.exam.dao.ExamTDao;
import com.wonders.stpt.exam.entity.ExamGroup;
import com.wonders.stpt.exam.entity.ExamMain;
import com.wonders.stpt.exam.service.ExamGroupService;

@Repository("examGroupService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ExamGroupServiceImpl implements ExamGroupService{
	
	private ExamTDao<ExamGroup> dao;
	public ExamTDao<ExamGroup> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("examTDao")ExamTDao<ExamGroup> dao) {
		this.dao = dao;
	}

	@Override
	public ExamGroup find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,ExamGroup.class);
	}
	@Override
	public List<ExamGroup> findByMainId(String mainId) {

		String hql = "from ExamGroup t where t.removed=0 and t.mainId ='"+mainId+"' order by t.groupNum asc,t.id desc ";
		return dao.findByHql(hql);
	}
	
	@Override
	public int deleteById(String id) {
		dao.excuteHQLUpdate("update ExamGroup set removed = 1 where id = ?", new Object[]{id});
		dao.excuteHQLUpdate("update ExamQuestion set removed = 1 where groupId = ?", new Object[]{id});
		dao.excuteHQLUpdate("update ExamOption set removed = 1 where groupId = ?", new Object[]{id});
		return 1;
	}

	@Override
	public ExamGroup save(ExamGroup bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	}
}
