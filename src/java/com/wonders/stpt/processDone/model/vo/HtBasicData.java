package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.HtBa;
import com.wonders.stpt.processDone.model.bo.HtXx;

@XmlRootElement(name="BasicData")
public class HtBasicData {
	@XmlElement(name="Htxx")
	private HtXx htxx;
	@XmlElement(name="Htba")
	private HtBa htba;
	
	public HtBasicData(HtXx htxx,HtBa htba){
		this.htxx = htxx;
		this.htba = htba;
	}
	
	public HtBasicData(){
		
	}
}
