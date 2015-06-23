package com.wonders.stpt.todoItem.model.vo;

import java.util.List;

public class TodoStatInfo {
	private int count;
	private int otCount;
	private List<TodoInfo> todoInfo;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getOtCount() {
		return otCount;
	}
	public void setOtCount(int otCount) {
		this.otCount = otCount;
	}
	public List<TodoInfo> getTodoInfo() {
		return todoInfo;
	}
	public void setTodoInfo(List<TodoInfo> todoInfo) {
		this.todoInfo = todoInfo;
	}
	
	
	
}
