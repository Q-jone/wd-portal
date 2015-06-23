package com.wonders.stpt.meetingCount.service;

import java.util.List;

public interface MeetingCountService {
	public List<Object[]> findBySql(String sql,List<Object> src);
}
