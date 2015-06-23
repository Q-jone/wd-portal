package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.HtBa;
import com.wonders.stpt.processDone.model.bo.HtXx;

@XmlRootElement(name="BasicData")
public class HtBasicDataWithStep {
	@XmlElement(name="Htxx")
	private HtXx htxx;
	@XmlElement(name="Htba")
	private HtBa htba;
	@XmlElement(name="ProcessCurrentStep")
	private String processCurrentStep;
	
	public HtBasicDataWithStep(String processCurrentStep,HtXx htxx,HtBa htba){
		this.htxx = htxx;
		this.htba = htba;
		this.processCurrentStep = processCurrentStep;
	}
	
	public HtBasicDataWithStep(){
		
	}
}
