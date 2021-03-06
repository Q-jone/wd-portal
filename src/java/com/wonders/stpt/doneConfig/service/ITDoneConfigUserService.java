package com.wonders.stpt.doneConfig.service;

import java.util.List;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.model.TDoneConfigUser;
/**
 * 服务接口
 * @author shanweifeng
 *
 */
public interface ITDoneConfigUserService {
	/**
	 * 保存
	 * @param tProcessConfig
	 * @return
	 * @throws Exception
	 */
	TDoneConfigUser save(TDoneConfigUser doneConfigUser)throws Exception;
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int delete(String[] ids)throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delete(String id)throws Exception;
	/**
	 * 根据主键查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	TDoneConfigUser findById(String id)throws Exception;
	/**
	 * 根据条件查找
	 * @param tProcessConfig
	 * @param pageindex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageResultSet<TDoneConfigUser> find(TDoneConfigUser doneConfigUser,Integer pageindex,Integer pageSize)throws Exception;
	/**
	 * 根据条件查找
	 * @param hql
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List<TDoneConfigUser> findList(String hql, Object[] obj)
			throws Exception;
}
