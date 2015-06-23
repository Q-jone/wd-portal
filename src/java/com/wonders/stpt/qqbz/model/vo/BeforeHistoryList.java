package com.wonders.stpt.qqbz.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.qqbz.model.bo.BeforeHistory;

@XmlRootElement(name="root")
public class BeforeHistoryList {
	@XmlElement(name="T_BEFORE_HISTORY")
	private List<BeforeHistory> beforeHistoryList;
	
	public BeforeHistoryList(List<BeforeHistory> beforeHistoryList){
		this.beforeHistoryList = beforeHistoryList;
	}
	
	public BeforeHistoryList(){
		
	}
}
