/**
 * 
 */
package com.wonders.stpt.pageNew.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.pageNew.dao.PageNewDao;
import com.wonders.stpt.pageNew.service.PageNewService;


/** 
 * @ClassName: PageServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午07:20:04 
 *  
 */
@Repository("pageNewService")
@Scope("prototype")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class PageNewServiceImpl implements PageNewService{
	private PageNewDao pageNewDao;

	public PageNewDao getPageNewDao() {
		return pageNewDao;
	}

	@Autowired(required=false)
	public void setPageNewDao(@Qualifier("pageNewDao")PageNewDao pageNewDao) {
		this.pageNewDao = pageNewDao;
	}
	
	public int countBySql(String sql){
		return this.pageNewDao.countBySql(sql);
	}
	public List<String[]> findPaginationInfo(String sql, int startRow, int pageSize){
		return this.pageNewDao.findPaginationInfo(sql, startRow, pageSize);
	}
	
	public int countByHql(String hql){
		return this.pageNewDao.countByHql(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List findPaginationInfoByHql(String hql, int startRow, int pageSize,Map<String, Object> filter,String orderSql) {
		return this.pageNewDao.findPaginationInfoByHql(hql, startRow, pageSize,filter,orderSql);
	}
}
