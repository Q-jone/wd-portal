package com.wonders.stpt.meetingCount.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.meetingCount.dao.MeetingCountDao;
import com.wonders.stpt.meetingCount.service.MeetingCountService;
@Repository("meetingCountService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class MeetingCountServiceImpl implements MeetingCountService{
	@Autowired
	private MeetingCountDao meetingCountDao;
	
	public List<Object[]> findBySql(String sql,List<Object> src){
		return meetingCountDao.findBySql(sql, src);
	}
}
