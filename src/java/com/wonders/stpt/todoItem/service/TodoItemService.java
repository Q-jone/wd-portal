/**
 * 
 */
package com.wonders.stpt.todoItem.service;

import com.wonders.stpt.todoItem.model.bo.TodoItem;
import com.wonders.stpt.todoItem.model.vo.TodoStatInfo;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: TodoItemService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午03:15:39 
 *  
 */
public interface TodoItemService {
	/**
	 * 根据Sql语句获得总记录数
	 * @return
	 */
	public int countBySql(String sql);
	/**
	 * 根据Hql语句获得总记录数
	 * @return
	 */
	/**
	 * 分页查询
	 */
	public int countByHql(String hql) ;
	
	public List<Map<String,String>> findPaginationInfo(String sql, int startRow, int pageSize);
	
	@SuppressWarnings("unchecked")
	public List findPaginationInfoByHql(String hql, int startRow, int pageSize) ;
	
	public List<TodoItem> getTodoItems(String loginName,String ologinName,String deptId,String type);
	
	public TodoStatInfo getTodoInfo(List<String> source);
}

