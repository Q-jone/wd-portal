/**
 * 
 */
package com.wonders.stpt.todoItem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.todoItem.dao.TodoItemDao;
import com.wonders.stpt.todoItem.model.bo.TodoItem;
import com.wonders.stpt.todoItem.model.vo.TodoInfo;
import com.wonders.stpt.todoItem.model.vo.TodoStatInfo;
import com.wonders.stpt.todoItem.service.TodoItemService;

/** 
 * @ClassName: TodoItemServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午03:15:46 
 *  
 */
@Repository("todoItemService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class TodoItemServiceImpl implements TodoItemService{
	private TodoItemDao todoItemDao;
	
	public TodoItemDao getTodoItemDao() {
		return todoItemDao;
	}
	
	@Autowired(required=false)
	public void setTodoItemDao(@Qualifier("todoItemDao") TodoItemDao todoItemDao) {
		this.todoItemDao = todoItemDao;
	}
	/**
	 * 根据Sql语句获得总记录数
	 * @return
	 */
	public int countBySql(String sql){
		return todoItemDao.countBySql(sql);
	}
	/**
	 * 根据Hql语句获得总记录数
	 * @return
	 */
	/**
	 * 分页查询
	 */
	public List<Map<String,String>> findPaginationInfo(String sql, int startRow, int pageSize){
		return todoItemDao.findPaginationInfo(sql, startRow, pageSize);
	}
	
	public int countByHql(String hql){
		return this.todoItemDao.countByHql(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List findPaginationInfoByHql(String hql, int startRow, int pageSize){
		return this.todoItemDao.findPaginationInfoByHql(hql, startRow, pageSize);
	}
	
	public List<TodoItem> getTodoItems(String loginName,String ologinName,String deptId,String type){
		return this.todoItemDao.getTodoItems(loginName,ologinName,deptId,type);
	}
	
	public TodoStatInfo getTodoInfo(List<String> source){
		return this.todoItemDao.getTodoInfo(source);
	}
}
