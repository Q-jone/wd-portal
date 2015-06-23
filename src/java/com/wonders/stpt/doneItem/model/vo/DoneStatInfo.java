package com.wonders.stpt.doneItem.model.vo;

import java.util.List;

public class DoneStatInfo {
	private int doneCount;
	private int unTrackCount;
	private List<String> type;
	private List<DoneInfo> doneInfo;
	
	
	public int getDoneCount() {
		return doneCount;
	}
	public void setDoneCount(int doneCount) {
		this.doneCount = doneCount;
	}
	public int getUnTrackCount() {
		return unTrackCount;
	}
	public void setUnTrackCount(int unTrackCount) {
		this.unTrackCount = unTrackCount;
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	public List<DoneInfo> getDoneInfo() {
		return doneInfo;
	}
	public void setDoneInfo(List<DoneInfo> doneInfo) {
		this.doneInfo = doneInfo;
	}
	
	
}
