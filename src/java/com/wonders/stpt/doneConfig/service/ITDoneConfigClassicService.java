package com.wonders.stpt.doneConfig.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.model.TDoneConfigClassic;
/**
 * 服务接口
 * @author shanweifeng
 *
 */
public interface ITDoneConfigClassicService {
	/**
	 * 保存
	 * @param tProcessConfig
	 * @return
	 * @throws Exception
	 */
	TDoneConfigClassic save(TDoneConfigClassic doneConfigUser)throws Exception;
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
	TDoneConfigClassic findById(String id)throws Exception;
	/**
	 * 根据条件查找
	 * @param tProcessConfig
	 * @param pageindex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageResultSet<TDoneConfigClassic> find(TDoneConfigClassic doneConfigUser,Integer pageindex,Integer pageSize)throws Exception;
	/**
	 * 根据类型名称查询记录主键
	 * @param name
	 * @return
	 * @throws Exception
	 */
	List getRefId(String[] name)throws Exception;
	/**
	 * 获取所有类型名称
	 * @return
	 * @throws Exception
	 */
	List getName()throws Exception;
	public List<TDoneConfigClassic> findByHql(String hql,Map param)throws Exception;
}
