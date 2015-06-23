package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.JobContact;
import com.wonders.stpt.processDone.model.bo.TDocSend;

@XmlRootElement(name="Datas")  
public class HtxxDataWithStep {
	@XmlElement(name="BasicData")
	private HtBasicDataWithStep htBasicData;
	public HtxxDataWithStep(HtBasicDataWithStep htBasicData){
		this.htBasicData = htBasicData;
	}
	
	public HtxxDataWithStep(){
		
	}
}
