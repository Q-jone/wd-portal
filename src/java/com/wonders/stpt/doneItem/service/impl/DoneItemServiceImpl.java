package com.wonders.stpt.doneItem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.doneItem.dao.DoneItemDao;
import com.wonders.stpt.doneItem.model.vo.DoneInfo;
import com.wonders.stpt.doneItem.model.vo.DoneStatInfo;
import com.wonders.stpt.doneItem.service.DoneItemService;


@Repository("doneItemService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class DoneItemServiceImpl implements DoneItemService{
	
	@Autowired(required=false)
	private DoneItemDao doneItemDao;
	
	public boolean track(List<String> source,String id,String trackStatus){
		return this.doneItemDao.track(source, id, trackStatus);
	}
	
	public DoneStatInfo getDoneInfo(List<String> source,String processStatus,String trackStatus){
		return this.doneItemDao.getDoneInfo(source,processStatus,trackStatus);
	}
	
	public List<String> getDoneType(List<String> source,String processStatus,String trackStatus){
		return this.doneItemDao.getDoneType(source, processStatus, trackStatus);
	}
	
	public Map<Integer,List<DoneInfo>> getDoneResult(List<String> source,String processStatus,String trackStatus){
		return this.doneItemDao.getDoneResult(source, processStatus, trackStatus);
	}

    public boolean trackBatch(List<String> source,String type,String trackStatus){
        return this.doneItemDao.trackBatch(source, type, trackStatus);
    }
}
