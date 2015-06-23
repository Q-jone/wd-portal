/**
 * 
 */
package com.wonders.stpt.validFile.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.validFile.dao.ValidFileDao;
import com.wonders.stpt.validFile.model.vo.ValidFileListVo;
import com.wonders.stpt.validFile.service.ValidFileService;

/** 
 * @ClassName: ValidFileServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月22日 上午11:32:39 
 *  
 */
@Service("validFileService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ValidFileServiceImpl implements ValidFileService{
	private ValidFileDao dao;

	public ValidFileDao getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("validFileDao")ValidFileDao dao) {
		this.dao = dao;
	}
	
	public PageResultSet<Map<String,Object>> list(StringBuffer sql ,ValidFileListVo vo){
		return this.dao.list(sql,vo);
	}

	
}
