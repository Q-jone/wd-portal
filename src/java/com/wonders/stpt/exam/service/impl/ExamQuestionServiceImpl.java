package com.wonders.stpt.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.exam.dao.ExamTDao;
import com.wonders.stpt.exam.entity.ExamOption;
import com.wonders.stpt.exam.entity.ExamQuestion;
import com.wonders.stpt.exam.service.ExamQuestionService;

@Repository("examQuestionService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ExamQuestionServiceImpl implements ExamQuestionService{
	
	private ExamTDao<ExamQuestion> dao;
	public ExamTDao<ExamQuestion> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("examTDao")ExamTDao<ExamQuestion> dao) {
		this.dao = dao;
	}

	@Override
	public ExamQuestion find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,ExamQuestion.class);
	}
	@Override
	public List<ExamQuestion> findByMainId(String mainId) {
		String hql = "from ExamQuestion t where t.removed=0 and t.mainId ='"+mainId+"' order by t.questNum asc,t.id desc ";
		return dao.findByHql(hql);
	}
	@Override
	public List<ExamQuestion> findByGroupId(String groupId) {
		String hql = "from ExamQuestion t where t.removed=0 and t.groupId ='"+groupId+"' order by t.questNum asc,t.id desc ";
		return dao.findByHql(hql);
	}
	
	@Override
	public ExamQuestion save(ExamQuestion q,String[] sid,String[] options) {
		if(q.getId()!=null&&!"".equals(q.getId())){
			q = dao.update(q);
			//optionDao.excuteHQLUpdate("delete from ExamOption where questId = ?", new Object[]{q.getId()});
		}else{
			q = dao.save(q);
		}
		
		if(options != null && options.length > 0){
			char opCode = 'A';
			List<ExamOption> saveOptions  = new ArrayList<ExamOption>();
			for(int i = 0;i<options.length;i++){
				ExamOption option = new ExamOption();
				if(!"".equals(sid[i])){
					option = this.optionDao.find(sid[i], ExamOption.class);
				}
				
				option.setQuestId(q.getId());
				option.setMainId(q.getMainId());
				option.setGroupId(q.getGroupId());
				option.setOpType(q.getQuestType());
				option.setOpCode(String.valueOf(opCode));
				option.setOpValue(options[i]);
				saveOptions.add(option);
				opCode = (char)(opCode + '\001');
			}
			optionDao.saveOrUpdateAll(saveOptions);		
		}else{
			ExamOption op = new ExamOption();
			if(sid != null && sid.length == 1 && !"".equals(sid[0])){
				op = this.optionDao.find(sid[0], ExamOption.class);
			}
			op.setQuestId(q.getId());
			op.setMainId(q.getMainId());
			op.setGroupId(q.getGroupId());
			op.setOpType(q.getQuestType());
			if(op.getId() == null){
				optionDao.save(op);	
			}else{
				optionDao.update(op);
			}
			
		}
		
		return q;
	}
	
	@Override
	public int deleteById(String id) {
		dao.excuteHQLUpdate("update ExamQuestion set removed = 1 where id = ?", new Object[]{id});
		dao.excuteHQLUpdate("update ExamOption set removed = 1 where questId = ?", new Object[]{id});
		return 1;
	}
	
	private ExamTDao<ExamOption> optionDao;
	public ExamTDao<ExamOption> getOptionDao() {
		return optionDao;
	}
	@Autowired(required=false)
	public void setOptionDao(@Qualifier("examTDao")ExamTDao<ExamOption> optionDao) {
		this.optionDao = optionDao;
	}
}
