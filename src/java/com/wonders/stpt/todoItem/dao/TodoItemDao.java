/**
 * 
 */
package com.wonders.stpt.todoItem.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.todoItem.model.bo.TodoItem;
import com.wonders.stpt.todoItem.model.vo.TodoInfo;
import com.wonders.stpt.todoItem.model.vo.TodoStatInfo;

/** 
 * @ClassName: TodoItemDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午03:10:51 
 *  
 */
public interface TodoItemDao {
	int countBySql(String sql);
	public int countByHql(String hql) ;
	public List<Map<String,String>> findPaginationInfo(String sql, int startRow, int pageSize);
	@SuppressWarnings("unchecked")
	public List findPaginationInfoByHql(String hql, int startRow, int pageSize) ;
	public List<TodoItem> getTodoItems(String loginName,String ologinName,String deptId,String type);
	public TodoStatInfo getTodoInfo(List<String> source);
}
