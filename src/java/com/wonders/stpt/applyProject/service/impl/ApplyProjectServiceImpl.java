/**
 * 
 */
package com.wonders.stpt.applyProject.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.applyProject.dao.ApplyProjectDao;
import com.wonders.stpt.applyProject.model.bo.TApplyProject;
import com.wonders.stpt.applyProject.model.vo.ApplyProjectListVo;
import com.wonders.stpt.applyProject.service.ApplyProjectService;
import com.wonders.stpt.page.model.PageResultSet;

/** 
 * @ClassName: ApplyProjectServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月30日 上午10:40:14 
 *  
 */

@Service("applyProjectService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ApplyProjectServiceImpl implements ApplyProjectService{
	private ApplyProjectDao dao;

	public ApplyProjectDao getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("applyProjectDao")ApplyProjectDao dao) {
		this.dao = dao;
	}
	/** 
	* @Title: save 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param bo
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public String save(TApplyProject bo) {
		return this.dao.save(bo);
	}
	/** 
	* @Title: update 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param bo
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public String update(TApplyProject bo) {
		return this.dao.update(bo);
	}
	/** 
	* @Title: load 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public TApplyProject load(String id) {
		return this.dao.load(id);
	}
	/** 
	* @Title: list 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param vo
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public PageResultSet<Map<String, Object>> list(ApplyProjectListVo vo) {
		return this.dao.list(vo);
	}
	/** 
	* @Title: listAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param vo
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public PageResultSet<Map<String, Object>> listAll(ApplyProjectListVo vo) {
		return this.dao.listAll(vo);
	}
	
	
	
}
