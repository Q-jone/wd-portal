/**
 * 
 */
package com.wonders.stpt.pageNew.dao;

import java.util.List;
import java.util.Map;
/** 
 * @ClassName: PageDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午07:19:51 
 *  
 */
public interface PageNewDao {
	public int countBySql(String sql);
	public int countByHql(String hql) ;
	public List<String[]> findPaginationInfo(String sql, int startRow, int pageSize);
	@SuppressWarnings("unchecked")
	public List findPaginationInfoByHql(String hql, int startRow, int pageSize,Map<String, Object> filter,String orderSql);
}
