/**
 * 
 */
package com.wonders.stpt.innerWork.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.innerWork.dao.InnerWorkDao;
import com.wonders.stpt.innerWork.model.bo.TInnerWork;
import com.wonders.stpt.innerWork.model.vo.InnerWorkListVo;
import com.wonders.stpt.innerWork.service.InnerWorkService;
import com.wonders.stpt.page.model.PageResultSet;

/** 
 * @ClassName: InnerWorkServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月7日 下午7:28:01 
 *  
 */
@Service("innerWorkService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class InnerWorkServiceImpl implements InnerWorkService{
	private InnerWorkDao innerWorkDao;

	public InnerWorkDao getInnerWorkDao() {
		return innerWorkDao;
	}
	@Autowired(required=false)
	public void setInnerWorkDao(@Qualifier("innerWorkDao")InnerWorkDao innerWorkDao) {
		this.innerWorkDao = innerWorkDao;
	}
	
	public String save(TInnerWork bo){
		return this.innerWorkDao.save(bo);
	}
	public String update(TInnerWork bo){
		return this.innerWorkDao.update(bo);
	}
	
	public TInnerWork load(String id){
		return this.innerWorkDao.load(id);
	}
	
	public PageResultSet<Map<String,Object>> list(InnerWorkListVo vo){
		return this.innerWorkDao.list(vo);
	}
	
	public PageResultSet<Map<String,Object>> listAll(InnerWorkListVo vo){
		return this.innerWorkDao.listAll(vo);
	}
	@Override
	public List<TInnerWork> findByStatus() {
		
		return this.innerWorkDao.findByStatus();
	}
}
