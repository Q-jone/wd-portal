package com.wonders.stpt.doneConfig.service;

import java.util.List;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.model.TProcessConfig;

/**
 * 服务接口
 * @author shanweifeng
 *
 */
public interface ITProcessConfigService {
	/**
	 * 保存
	 * @param tProcessConfig
	 * @return
	 * @throws Exception
	 */
	TProcessConfig save(TProcessConfig tProcessConfig)throws Exception;
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
	TProcessConfig findById(String id)throws Exception;
	/**
	 * 根据条件查找
	 * @param tProcessConfig
	 * @param pageindex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageResultSet<TProcessConfig> find(TProcessConfig tProcessConfig,Integer pageindex,Integer pageSize)throws Exception;
	/**
	 * 根据id数组查询记录(0全/1反)
	 * @param ids
	 * @param type(0全/1反)
	 * @return
	 * @throws Exception
	 */
	List<TProcessConfig> findByIds(String[] ids,int type)throws Exception;
	/**
	 * 根据hql语句获取相应记录
	 * @param hql
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	List<TProcessConfig> findByHQL(String hql,Object[] obj)throws Exception;
	
	/**
	 * 将记录从类型中剔除
	 * @param hql
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	int deleteByType(String hql,Object[] obj)throws Exception;
}
