package com.wonders.stpt.doneItem.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.doneItem.model.vo.DoneInfo;
import com.wonders.stpt.doneItem.model.vo.DoneStatInfo;

public interface DoneItemDao {
	public boolean track(List<String> source,String id,String trackStatus);
	public DoneStatInfo getDoneInfo(List<String> source,String processStatus,String trackStatus);
	public List<String> getDoneType(List<String> source,String processStatus,String trackStatus);
	public Map<Integer,List<DoneInfo>> getDoneResult(List<String> source,String processStatus,String trackStatus);
    public boolean trackBatch(List<String> source,String type,String trackStatus);
}
