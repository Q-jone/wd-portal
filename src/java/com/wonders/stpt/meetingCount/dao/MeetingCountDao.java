package com.wonders.stpt.meetingCount.dao;

import java.util.List;

public interface MeetingCountDao {
	public List<Object[]> findBySql(String sql,List<Object> src);
}
