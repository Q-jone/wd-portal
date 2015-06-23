package com.wonders.stpt.doneConfig.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.model.TDoneConfigClassic;
import com.wonders.stpt.doneConfig.model.TDoneConfigInfo;

public interface TDoneConfigInfoDao {
		/**
		 * 保存
		 * @param tProcessConfig
		 * @return
		 * @throws Exception
		 */
	TDoneConfigInfo save(TDoneConfigInfo tDoneConfigInfo)throws Exception;
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
		TDoneConfigInfo findById(String id)throws Exception;
		/**
		 * 根据条件查找
		 * @param tProcessConfig
		 * @param pageindex
		 * @param pageSize
		 * @return
		 * @throws Exception
		 */
		PageResultSet<TDoneConfigInfo> find(TDoneConfigInfo tDoneConfigInfo,Integer pageindex,Integer pageSize)throws Exception;
		/**
		 * 根据id数组查询记录(0全/1反)
		 * @param ids
		 * @param type(0全/1反)
		 * @return
		 * @throws Exception
		 */
		List<TDoneConfigInfo> findByIds(String[] ids,int type)throws Exception;
		/**
		 * 根据hql语句获取相应记录
		 * @param hql
		 * @param obj
		 * @return
		 * @throws Exception
		 */
		List<Object[]> findByHQL(String hql,Map param)throws Exception;
		List<TDoneConfigInfo> findByHQL(String hql,Object[] obj)throws Exception;
		/**
		 * 
		 * @param loginName
		 * @return
		 * @throws Exception
		 */
		List<TDoneConfigInfo> findByLoginName(String loginName)throws Exception;
		
		List<TDoneConfigClassic> getType(String hql,Object[] obj)throws Exception;
		/**
		 * 根据hql语句获取最大的orders
		 * @param hql
		 * @param obj
		 * @return
		 * @throws Exception
		 */
		String getMaxOrders(String hql,Object[] obj)throws Exception;
		/**
		 * 跟据hql语句删除记录
		 * @param hql
		 * @param obj
		 * @return
		 * @throws Exception
		 */
		int delete(String hql,Object[] obj)throws Exception;
		/**
		 * 
		 * @param hql
		 * @param param
		 * @return
		 */
		int deleteByHql(String hql,Map param);
	}
